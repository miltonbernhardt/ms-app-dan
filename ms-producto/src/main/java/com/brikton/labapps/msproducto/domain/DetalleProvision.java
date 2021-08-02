package com.brikton.labapps.msproducto.domain;

import javax.persistence.*;

@Entity
public class DetalleProvision {

    @OneToOne
    @JoinColumn(name = "id_material")
    private Material material;

    @ManyToOne
    @JoinColumn(name = "id_provision")
    private Provision provision;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_detalle_provision")
    private Integer id;
    private Integer cantidad;

    public DetalleProvision() {
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Provision getProvision() {
        return provision;
    }

    public void setProvision(Provision provision) {
        this.provision = provision;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "DetalleProvision{" +
                "material=" + material +
                ", provision=" + provision +
                ", id=" + id +
                ", cantidad=" + cantidad +
                '}';
    }
}
