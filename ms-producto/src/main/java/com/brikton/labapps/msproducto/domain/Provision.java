package com.brikton.labapps.msproducto.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Provision {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_provision")
    private List<DetalleProvision> detalleProvision;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_provision")
    private Integer id;
    private LocalDate fechaProvision;

    public Provision() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaProvision() {
        return fechaProvision;
    }

    public void setFechaProvision(LocalDate fechaProvision) {
        this.fechaProvision = fechaProvision;
    }

    public List<DetalleProvision> getDetalleProvision() {
        return detalleProvision;
    }

    public void setDetalleProvision(List<DetalleProvision> detalleProvision) {
        this.detalleProvision = detalleProvision;
    }

    @Override
    public String toString() {
        return "Provision{" +
                "detalleProvision=" + detalleProvision +
                ", id=" + id +
                ", fechaProvision=" + fechaProvision +
                '}';
    }
}
