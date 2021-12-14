package com.jessicavera.appmovilcaso1.modelo;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SuperHelper extends SQLiteOpenHelper {

    private static final String DATABASE="cotizar.db";
    Context miContext;

    public SuperHelper(Context context) {
        super(context, DATABASE,null,1);
        miContext=context;
        File pathArchivo=miContext.getDatabasePath(DATABASE);

        //verificar archivo
        if(!verificaBase(pathArchivo.getAbsolutePath())){

            //COPIAR ARCHIVO
            try {
                copiarBase(pathArchivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //COPIAR BASE
    private  void copiarBase(File rutaBase) throws IOException {
        InputStream miInput = miContext.getAssets().open(DATABASE);
        OutputStream miOutput= new FileOutputStream(rutaBase);
        byte[] buffer=new byte[1024];
        int largo;
        while ((largo=miInput.read(buffer))>0){
            miOutput.write(buffer,0,largo);
        }
        miOutput.flush();
        miOutput.close();
        miInput.close();
    }

    //VERIFICAR ARCHIVO
    private boolean verificaBase(String ruta){
        SQLiteDatabase miBase=null;
        try {
            miBase = SQLiteDatabase.openDatabase(ruta, null, SQLiteDatabase.OPEN_READONLY);

        }catch (Exception ex) {
        }
        if (miBase!=null){
            miBase.close();
        }

        return miBase!=null;
    }

    //Inserta un cliente
    public String insertarcotizacion(Cotizacion cotizacion){
        String SQLi="";
        SQLi+="insert into cotizar  (id_cotizar,cedula,nombre,apellido,correo,fecha_nacimiento,modelo,marca)";
        SQLi+=" values (";
        SQLi+="'"+cotizacion.getId_cotizacion()+"'";
        SQLi+=",'"+cotizacion.getCedula()+"'";
        SQLi+=",'"+cotizacion.getNombre()+"'";
        SQLi+=",'"+cotizacion.getApellido()+"'";
        SQLi+=",'"+cotizacion.getCorreo()+"'";
        SQLi+=",'"+cotizacion.getFecha_nacimiento()+"'";
        SQLi+=",'"+cotizacion.getModelo()+"'";
        SQLi+=",'"+cotizacion.getMarca()+"'";
        SQLi+=")";
        try {
            this.getWritableDatabase().execSQL(SQLi);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
        return null;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
