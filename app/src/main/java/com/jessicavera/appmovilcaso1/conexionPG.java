package com.jessicavera.appmovilcaso1;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexionPG {
    Connection conexion = null;
    public Connection connectionBD(){
        try {
            Class.forName("org.postgresql.Driver");
            conexion= DriverManager.getConnection("jdbc:postgresql://localhost:5432/Pruebaconexion","postgres","Melicris0636....");
        }catch (Exception er) {
            System.err.println(er.getMessage());
        }
        return  conexion;
    }
        protected void cerrar_conexion (Connection con)throws Exception{
        con.close();
    }
}
