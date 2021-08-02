package com.brikton.labapps.msproducto.domain;

import javax.persistence.*;

@Entity
public class Unidad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_unidad")
    private Integer id;
    private String descripcion;

    public Unidad() {

    }

    public Integer getId() {
        if(id == null) return -1;
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

    @Override
    public String toString() {
        return "Unidad{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
