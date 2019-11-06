package com.example.proyectofinal;

import java.io.Serializable;

public class MontoDto implements Serializable {
    private Integer idmonto;
  private   String fecha;
   private Double ingreso;

    public MontoDto(Integer idmonto, String fecha, Double ingreso) {
        this.idmonto = idmonto;
        this.fecha = fecha;
        this.ingreso = ingreso;

    }

    public MontoDto() {

    }

    public Integer getIdmonto() {
        return idmonto;
    }

    public void setIdmonto(Integer idmonto) {
        this.idmonto = idmonto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getIngreso() {
        return ingreso;
    }

    public void setIngreso(Double ingreso) {
        this.ingreso = ingreso;
    }
}
