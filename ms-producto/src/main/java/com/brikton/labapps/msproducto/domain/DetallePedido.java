package com.brikton.labapps.msproducto.domain;

import javax.persistence.*;

// Solo lectura
@Entity
public class DetallePedido {

    @OneToOne
    @JoinColumn(name = "id_material")
    private Material material;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_detalle_pedido")
    private Integer id;
    private Integer cantidad;

    public DetallePedido() {

    }

    public Integer getId() {
        return id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return "DetallePedido{" +
                "material=" + material +
                ", id=" + id +
                ", cantidad=" + cantidad +
                '}';
    }
}
