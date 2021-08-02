package com.brikton.labapps.mspedidos.dto;

import java.io.Serializable;

import com.brikton.labapps.mspedidos.domain.DetallePedido;

public class DetallePedidoDTO implements Serializable {

    private Integer idMaterial;
    private Integer cantidad;

    public DetallePedidoDTO(){}

    public DetallePedidoDTO(DetallePedido d){
        this.idMaterial = d.getProducto().getId();
        this.cantidad = d.getCantidad();
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
