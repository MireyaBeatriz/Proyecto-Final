package com.example.proyectofinal;

import java.io.Serializable;

public class MontoDto implements Serializable {
    private int idmonto;
  private   String fecha;
   private int ingreso;

    public MontoDto(int idmonto, String fecha, int ingreso) {
        this.idmonto = idmonto;
        this.fecha = fecha;
        this.ingreso = ingreso;

    }

    public MontoDto() {

    }

    public int getIdmonto() {
        return idmonto;
    }

    public void setIdmonto(int idmonto) {
        this.idmonto = idmonto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIngreso() {
        return ingreso;
    }

    public void setIngreso(int ingreso) {
        this.ingreso = ingreso;
    }
}
