package com.brikton.labapps.msproducto.dto;

import java.io.Serializable;


public class DetallePedido implements Serializable {

    private Integer idMaterial;
    private Integer cantidad;

    public DetallePedido(){}

    public DetallePedido(String idMaterial,String cantidad){
        this.idMaterial = Integer.valueOf(idMaterial);
        this.cantidad = Integer.valueOf(cantidad);
    }

    public Integer getIdMaterial() {
        return idMaterial;
    }
    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "DetallePedidoDTO [cantidad=" + cantidad + ", idMaterial=" + idMaterial + "]";
    }

}
