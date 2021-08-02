package com.brikton.labapps.mscuentacorriente.dto;

public class Factura {
    private Integer fechaPedido;
    private String estado;
    private String obra;

    public Factura() {
    }

    public Integer getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Integer fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObra() {
        return obra;
    }

    public void setObra(String obra) {
        this.obra = obra;
    }
}
