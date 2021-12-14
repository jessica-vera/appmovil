package com.jessicavera.appmovilcaso1.modelo;

import java.util.Date;

public class Cotizacion {

    private int id_cotizacion;
    private String cedula;
    private String nombre;
    private String apellido;
    private String correo;
    private String fecha_nacimiento;
    private String modelo;
    private String marca;

    public Cotizacion(){

    }

    public Cotizacion(int id_cotizacion, String cedula, String nombre, String apellido, String correo, String fecha_nacimiento, String modelo, String marca) {
        this.id_cotizacion = id_cotizacion;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.modelo = modelo;
        this.marca = marca;
    }

    public int getId_cotizacion() {
        return id_cotizacion;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setId_cotizacion(int id_cotizacion) {
        this.id_cotizacion = id_cotizacion;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
