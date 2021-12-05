package com.jessicavera.appmovilcaso1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button cotizar;
    Button garantia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cotizar = (Button)findViewById(R.id.bnt_cotizar);
        garantia = (Button)findViewById(R.id.bnt_solicitargarantia);

        cotizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, cotizar.class);
                startActivity(i);

            }
        });
        garantia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, garantia.class);
                startActivity(i);
            }
        });
    }


}
