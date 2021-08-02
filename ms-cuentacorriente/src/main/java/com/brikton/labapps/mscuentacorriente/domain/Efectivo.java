package com.brikton.labapps.mscuentacorriente.domain;

import javax.persistence.Entity;

@Entity
public class Efectivo extends MedioPago {

    private Integer nroRecibo;

    public Efectivo() {
    }

    public Integer getNroRecibo() {
        return nroRecibo;
    }

    public void setNroRecibo(Integer nroRecibo) {
        this.nroRecibo = nroRecibo;
    }
}
