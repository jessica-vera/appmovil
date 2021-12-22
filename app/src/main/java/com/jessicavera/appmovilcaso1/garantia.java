package com.jessicavera.appmovilcaso1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.CallableStatement;
import java.sql.Types;

public class garantia extends AppCompatActivity {
    private static conexionPG con = new conexionPG();
    Button btningreso;
    EditText txtusuario, txtclave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garantia);

        btningreso=findViewById(R.id.btnIngresar);
        txtusuario=findViewById(R.id.txtUsuario);
        txtclave=findViewById(R.id.txtClave);

        btningreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(garantia.this, garantia2.class);
                startActivity(i);
            }
        });

    }
    public void Inicio_sesion (String usuario, String clave  ){
        try {
            String storeProcedureCall="{CALL user_acceso(?,?)}";
            CallableStatement callableStatement = con.connectionBD().prepareCall(storeProcedureCall);
            callableStatement.setString(1,usuario);
            callableStatement.setString(2,clave);

            callableStatement.executeUpdate();

            


        }catch (Exception er){

        }


    }
}
