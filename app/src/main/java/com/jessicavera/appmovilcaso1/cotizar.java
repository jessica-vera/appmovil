package com.jessicavera.appmovilcaso1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.jessicavera.appmovilcaso1.modelo.Cotizacion;
import com.jessicavera.appmovilcaso1.modelo.SuperHelper;

import java.util.Calendar;
import java.util.Locale;

public class cotizar extends AppCompatActivity {

    EditText cedula, nombre, apellido, correo, fecha_nacimiento;
    Spinner modelo, marca;
    CheckBox condicion;
    Button bnt_fecha_N, bntcotizar;
    EditText txt_date;
    Button enviarcotizacion;
    private int dia, mes, anio;
    private int resta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotizar);
        enviarcotizacion = (Button)findViewById(R.id.bnt_cotizarvehiculo);
        bnt_fecha_N = (Button)findViewById(R.id.bnt_fecha_n);
        txt_date = (EditText)findViewById(R.id.txt_date);

        //varibles para los EditText
        cedula = (EditText)findViewById(R.id.txt_cedula);
        nombre = (EditText)findViewById(R.id.txt_nombrecotizar);
        apellido = (EditText)findViewById(R.id.txt_apellidocotizar);
        correo = (EditText)findViewById(R.id.txt_emailcotizar);
        fecha_nacimiento = (EditText)findViewById(R.id.txt_date);
        modelo = (Spinner)findViewById(R.id.id_spinner_modelo);
        marca  = (Spinner)findViewById(R.id.id_spinner_marca);
        bntcotizar = (Button)findViewById(R.id.bnt_cotizarvehiculo);
        condicion = (CheckBox)findViewById(R.id.box_condicion);

        //metodo boton enviar cotizacion
        enviarcotizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cedula.getText().toString().isEmpty()||nombre.getText().toString().isEmpty()||apellido.getText().toString().isEmpty()||correo.getText().toString().isEmpty()||fecha_nacimiento.getText().toString().isEmpty()){

                    Toast.makeText(cotizar.this,"Llenar Formulario", Toast.LENGTH_LONG).show();

                } else {

                    SuperHelper superHelper = new SuperHelper(cotizar.this);
                    Cotizacion cotizacion = new Cotizacion();
                    cotizacion.setCedula(cedula.getText().toString());
                    cotizacion.setNombre(nombre.getText().toString().toLowerCase());
                    cotizacion.setApellido(apellido.getText().toString().toLowerCase());
                    cotizacion.setCorreo(correo.getText().toString());
                    cotizacion.setFecha_nacimiento(fecha_nacimiento.getText().toString());
                    cotizacion.setModelo(modelo.getSelectedItem().toString());
                    cotizacion.setMarca(marca.getSelectedItem().toString());

                    String sentencia = superHelper.insertarcotizacion(cotizacion);

                    Toast.makeText(cotizar.this, "Cotizacion Enviada y Registrada Correctamente", Toast.LENGTH_LONG).show();

                    Intent env = new Intent(cotizar.this, mensaje.class);
                    startActivity(env);
                }
            }
        });

        // metodo boton ver
        bnt_fecha_N.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v==bnt_fecha_N){
                    final Calendar calendar = Calendar.getInstance();
                    dia= calendar.get(Calendar.DAY_OF_MONTH);
                    mes= calendar.get(Calendar.MONTH);
                    anio = calendar.get(Calendar.YEAR);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(cotizar.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            txt_date.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                            resta=2021-year;
                            if(resta > 17 ){
                                Toast.makeText(cotizar.this,"Es mayor de Edad", Toast.LENGTH_LONG).show();
                            }else{
                                if(resta < 17){
                                    Toast.makeText(cotizar.this,"Es menor de Edad, no se le permite realizar una cotización", Toast.LENGTH_LONG).show();
                                }

                            }
                        }
                    }

                    , dia, mes, anio);
                    datePickerDialog.show();
                }

            }
        });

    }

}