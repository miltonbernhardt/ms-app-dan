package com.brikton.labapps.msproducto.domain;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class MovimientoStock {

    @OneToOne
    @JoinColumn(name = "id_material")
    private Material material;
    @OneToOne
    @JoinColumn(name = "id_detalle_provision")
    private DetalleProvision detalleProvision;
    @OneToOne
    @JoinColumn(name = "id_detalle_pedido")
    private DetallePedido detallePedido;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_movimiento_stock")
    private Integer id;
    private Integer cantidadEntrada;
    private Integer cantidadSalida;
    private Instant fecha;

    public MovimientoStock() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidadEntrada() {
        return cantidadEntrada;
    }

    public void setCantidadEntrada(Integer cantidadEntrada) {
        this.cantidadEntrada = cantidadEntrada;
    }

    public Integer getCantidadSalida() {
        return cantidadSalida;
    }

    public void setCantidadSalida(Integer cantidadSalida) {
        this.cantidadSalida = cantidadSalida;
    }

    public Instant getFecha() {
        return fecha;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public DetalleProvision getDetalleProvision() {
        return detalleProvision;
    }

    public void setDetalleProvision(DetalleProvision detalleProvision) {
        this.detalleProvision = detalleProvision;
    }

    public DetallePedido getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(DetallePedido detallePedido) {
        this.detallePedido = detallePedido;
    }

    @Override
    public String toString() {
        return "MovimientoStock{" +
                "material=" + material +
                ", detalleProvision=" + detalleProvision +
                ", detallePedido=" + detallePedido +
                ", id=" + id +
                ", cantidadEntrada=" + cantidadEntrada +
                ", cantidadSalida=" + cantidadSalida +
                ", fecha=" + fecha +
                '}';
    }
}
