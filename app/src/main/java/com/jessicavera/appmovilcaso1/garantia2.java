package com.jessicavera.appmovilcaso1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class garantia2 extends AppCompatActivity {
    Button solicitargarantia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garantia2);
        solicitargarantia=findViewById(R.id.btnSolicGarantia);

        solicitargarantia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(garantia2.this, garantia3.class);
                startActivity(i);
            }
        });
    }

}