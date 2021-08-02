package com.brikton.labapps.mscuentacorriente.dto;

import java.time.Instant;

public class Pedido {
    private EstadoPedido estadoPedido;
    private Integer id;
    private Instant fechaPedido;

    public Pedido() {
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Instant fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
}
