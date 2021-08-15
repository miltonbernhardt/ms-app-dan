package com.brikton.labapps.msusuario.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties(value = { "obras" })
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_cliente")
	private Integer id;
	@NotNull
	private String razonSocial;
	private String cuit;
	@NotNull
	private String mail;
	private Double maxCuentaCorriente;
	private Date fechaBaja;
	private Boolean habilitadoOnline;
	
	@OneToMany(mappedBy = "cliente")
	private List<Obra> obras;
	
	@OneToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Double getMaxCuentaCorriente() {
		return maxCuentaCorriente;
	}
	public void setMaxCuentaCorriente(Double maxCuentaCorriente) {
		this.maxCuentaCorriente = maxCuentaCorriente;
	}
	public Boolean getHabilitadoOnline() {
		return habilitadoOnline;
	}
	public void setHabilitadoOnline(Boolean habilitadoOnline) {
		this.habilitadoOnline = habilitadoOnline;
	}
	public List<Obra> getObras() {
		return obras;
	}
	public void setObras(List<Obra> obras) {
		this.obras = obras;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
	public void agregarObra(Obra obra) {
		this.obras.add(obra);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
