package com.jessicavera.appmovilcaso1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class garantia3 extends AppCompatActivity {
    Button regresarinicio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garantia3);
        regresarinicio=findViewById(R.id.btnRegresoInicio);
        regresarinicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(garantia3.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}