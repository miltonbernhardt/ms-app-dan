package com.brikton.labapps.mscuentacorriente.domain;

import javax.persistence.Entity;
import java.time.Instant;

@Entity
public class Cheque extends MedioPago {

    private Integer nroCheque;
    private Instant fechaCobro;
    private String banco;

    public Cheque() {

    }

    public Integer getNroCheque() {
        return nroCheque;
    }

    public void setNroCheque(Integer nroCheque) {
        this.nroCheque = nroCheque;
    }

    public Instant getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(Instant fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }
}
