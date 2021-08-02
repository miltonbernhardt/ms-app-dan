package com.brikton.labapps.mspedidos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class DetallePedido {
	
	@Id
	@Column(name = "id_detalle")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer cantidad;
	private Double precio;
	@OneToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;
	
	public DetallePedido(){}
	
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
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
