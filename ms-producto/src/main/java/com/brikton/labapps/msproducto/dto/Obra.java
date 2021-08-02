package com.brikton.labapps.msproducto.dto;

import java.io.Serializable;

public class Obra implements Serializable {

    private Integer id;
    private String descripcion;

    public Obra() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    ;

}
    
