package com.example.proyectofinal;

import java.io.Serializable;

public class GastosDto implements Serializable  {
    private int idgasto;
    private String et_descripcion;
    private String et_fecha;
    private double et_monto;


    public GastosDto(String et_descripcion, String et_fecha, double et_monto){
        this.et_descripcion = et_descripcion;
        this.et_fecha = et_fecha;
        this.et_monto = et_monto;
    }

    public String getEt_descripcion() {
        return et_descripcion;
    }

    public void setEt_descripcion(String et_descripcion) {
        this.et_descripcion = et_descripcion;
    }

    public String getEt_fecha() {
        return et_fecha;
    }

    public void setEt_fecha(String et_fecha) {
        this.et_fecha = et_fecha;
    }

    public double getEt_monto() {
        return et_monto;
    }

    public void setEt_monto(double et_monto) {
        this.et_monto = et_monto;
    }

    public int getIdgasto() {
        return idgasto;
    }

    public void setIdgasto(int idgasto) {
        this.idgasto = idgasto;
    }

    public GastosDto(){

    }
}
