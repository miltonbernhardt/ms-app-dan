package com.brikton.labapps.mspedidos.domain;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Pedido {

    @Id
    @Column(name = "id_pedido")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	private Instant fechaPedido;
    @OneToOne
    @JoinColumn(name = "id_obra")
    private Obra obra;
    private EstadoPedido estado;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pedido")
    private List<DetallePedido> detalle;

    
    public Pedido(){
        this.obra = null;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Instant getFechaPedido() {
        return fechaPedido;
    }
    public void setFechaPedido(Instant fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
    public Obra getObra() {
        return obra;
    }
    public void setObra(Obra obra) {
        this.obra = obra;
    }
    public List<DetallePedido> getDetalle() {
        return detalle;
    }
    public void setDetalle(List<DetallePedido> detalle) {
        this.detalle = detalle;
    }
    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public void agregarDetalle(DetallePedido detalle2) {
        this.detalle.add(detalle2);
    }


}
