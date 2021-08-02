package com.brikton.labapps.mscuentacorriente.domain;

import javax.persistence.Entity;

@Entity
public class Transferencia extends MedioPago {

    private String cbuOrigen;
    private String cbuDestino;
    private Long codigoTransferencia;

    public Transferencia() {
    }

    public String getCbuOrigen() {
        return cbuOrigen;
    }

    public void setCbuOrigen(String cbuOrigen) {
        this.cbuOrigen = cbuOrigen;
    }

    public String getCbuDestino() {
        return cbuDestino;
    }

    public void setCbuDestino(String cbuDestino) {
        this.cbuDestino = cbuDestino;
    }

    public Long getCodigoTransferencia() {
        return codigoTransferencia;
    }

    public void setCodigoTransferencia(Long codigoTransferencia) {
        this.codigoTransferencia = codigoTransferencia;
    }
}
