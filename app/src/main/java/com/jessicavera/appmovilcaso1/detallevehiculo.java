package com.jessicavera.appmovilcaso1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class detallevehiculo extends AppCompatActivity {

    Button detalle ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallevehiculo);

        detalle = (Button)findViewById(R.id.bnt_cotizarvehiculo);

        detalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(detallevehiculo.this, mensaje.class);
                startActivity(i);
            }
        });
    }
}