package com.jessicavera.appmovilcaso1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class cotizar extends AppCompatActivity {

    Button bnt_fecha_N;
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


        enviarcotizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent env = new Intent(cotizar.this, mensaje.class);
                startActivity(env);
            }
        });

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