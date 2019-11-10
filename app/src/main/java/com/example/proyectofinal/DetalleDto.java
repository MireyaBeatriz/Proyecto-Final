package com.example.proyectofinal;

import java.io.Serializable;

public class DetalleDto implements Serializable {

    private Integer id_totalmonto;
    private String detalle;


    public DetalleDto(Integer id_totalmonto, String detalle) {
        this.id_totalmonto = id_totalmonto;
        this.detalle = detalle;


    }

    public DetalleDto() {

    }

    public Integer getId_totalmonto() {
        return id_totalmonto;
    }

    public void setId_totalmonto(Integer id_totalmonto) {
        this.id_totalmonto = id_totalmonto;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
