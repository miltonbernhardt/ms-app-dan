package com.brikton.labapps.mscuentacorriente.domain;

import javax.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cliente")
    private Integer id;
    private String razonSocial;
    private String cuit;
    private String mail;

    public Cliente() {
    }

    public Integer getId() {
        return id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public String getMail() {
        return mail;
    }
}
