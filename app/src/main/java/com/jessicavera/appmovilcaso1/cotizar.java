package com.jessicavera.appmovilcaso1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class cotizar extends AppCompatActivity {

    Button enviarcotizacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotizar);
        enviarcotizacion = (Button)findViewById(R.id.bnt_cotizarvehiculo);

        enviarcotizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent env = new Intent(cotizar.this, mensaje.class);
                startActivity(env);
            }
        });
    }



}