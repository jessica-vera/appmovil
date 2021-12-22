package com.jessicavera.appmovilcaso1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jessicavera.appmovilcaso1.modelo.Cotizacion;
import com.jessicavera.appmovilcaso1.modelo.SuperHelper;

import java.util.Calendar;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class cotizar extends AppCompatActivity {

    EditText cedula, nombre, apellido, correo, fecha_nacimiento, clave;
    Spinner modelo, marca;
    CheckBox condicion;
    Button bnt_fecha_N, bntcotizar;
    EditText txt_date;
    Button enviarcotizacion;
    private int dia, mes, anio;
    private int resta;
    javax.mail.Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotizar);
        enviarcotizacion = (Button)findViewById(R.id.bnt_cotizarvehiculo);
        bnt_fecha_N = (Button)findViewById(R.id.bnt_fecha_n);
        txt_date = (EditText)findViewById(R.id.txt_date);

        //varibles para los EditText button y spinner y chechbox
        cedula = (EditText)findViewById(R.id.txt_cedula);
        nombre = (EditText)findViewById(R.id.txt_nombrecotizar);
        apellido = (EditText)findViewById(R.id.txt_apellidocotizar);
        correo = (EditText)findViewById(R.id.txt_emailcotizar);
        clave = (EditText) findViewById(R.id.txt_clavecorreo);
        fecha_nacimiento = (EditText)findViewById(R.id.txt_date);
        modelo = (Spinner)findViewById(R.id.id_spinner_modelo);
        marca  = (Spinner)findViewById(R.id.id_spinner_marca);
        bntcotizar = (Button)findViewById(R.id.bnt_cotizarvehiculo);
        condicion = (CheckBox)findViewById(R.id.box_condicion);

        //metodo boton enviar cotizacion
        enviarcotizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //metodo para enviar un email
                enviaremailcotizacion();
                //metodo para guardar la cotizacion
                guardarcotizacion();
                //enviar2();
            }
        });

        // metodo boton selecionar fecha de nacimiento
        bnt_fecha_N.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecionarfechanacimiento(v);
            }
        });
    }
    //Metodo seleccionar fecha de nacimiento
    private void selecionarfechanacimiento(View v){
        if (v==bnt_fecha_N){

            final Calendar calendar = Calendar.getInstance();
            dia= calendar.get(Calendar.DAY_OF_MONTH);
            mes= calendar.get(Calendar.MONTH);
            anio = calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(cotizar.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    txt_date.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                    resta=2022-year;
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

    //Metodo para guardar la cotizacion en la base de datos sqlite
    private void guardarcotizacion() {
        if(cedula.getText().toString().isEmpty()||nombre.getText().toString().isEmpty()||apellido.getText().toString().isEmpty()||correo.getText().toString().isEmpty()||fecha_nacimiento.getText().toString().isEmpty() || modelo.getSelectedItem().toString().isEmpty() || marca.getSelectedItem().toString().isEmpty()){

            Toast.makeText(cotizar.this,"Llenar Formulario y Aceptar Terminos y Condiciones", Toast.LENGTH_LONG).show();

        } else {

            SuperHelper superHelper = new SuperHelper(cotizar.this);
            Cotizacion cotizacion = new Cotizacion();
            cotizacion.setCedula(cedula.getText().toString());
            cotizacion.setNombre(nombre.getText().toString().toLowerCase());
            cotizacion.setApellido(apellido.getText().toString().toLowerCase());
            cotizacion.setCorreo(correo.getText().toString());
            //cotizacion.setClave_correo(clave.getText().toString());
            cotizacion.setFecha_nacimiento(fecha_nacimiento.getText().toString());
            cotizacion.setModelo(modelo.getSelectedItem().toString());
            cotizacion.setMarca(marca.getSelectedItem().toString());


                //guardar la cotizacion en la base
                String sentencia = superHelper.insertarcotizacion(cotizacion);
                //mensaje
                Toast.makeText(cotizar.this, "Cotizacion Enviada y Registrada", Toast.LENGTH_LONG).show();
                //Abrir el siguiente activity
                Intent env = new Intent(cotizar.this, mensaje.class);
                startActivity(env);

        }
    }

    private void enviaremailcotizacion(){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Properties properties = new Properties();
        properties.put("mail.smtp.host","smtp.googlemail.com");
        properties.put("mail.smtp.socketFactory.port","465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port","465");

        try{

            session=Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(correo.getText().toString(), clave.getText().toString());
                }
            });

            if(session!=null){
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(correo.getText().toString())); // emisor
                message.setSubject("Cotizacion de un vehiculo");
                //receptor
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("veramogrovejo@gmail.com"));

                message.setContent(cedula.getText().toString(), "text/html; charset=utf-8");
                message.setContent(nombre.getText().toString(), "text/html; charset=utf-8");
                message.setContent(apellido.getText().toString(), "text/html; charset=utf-8");
                message.setContent(modelo.getSelectedItem().toString(), "text/html; charset=utf-8");
                message.setContent(marca.getSelectedItem().toString(), "text/html; charset=utf-8");
                Transport.send(message);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*private void enviar2(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"+ clave.getText().toString()));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Solicitar una cotizacion del siguiente vehiculo");
        intent.putExtra(Intent.EXTRA_TEXT, cedula.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT, nombre.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT, apellido.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT, modelo.getSelectedItem().toString());
        intent.putExtra(Intent.EXTRA_TEXT, marca.getSelectedItem().toString());
        startActivity(intent);
    }*/
}