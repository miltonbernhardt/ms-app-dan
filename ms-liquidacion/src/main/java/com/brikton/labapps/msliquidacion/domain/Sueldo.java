package com.brikton.labapps.msliquidacion.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Sueldo {

  @Id
  @Column(name = "id_sueldo")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private Double monto;
  private Double comision;

  @OneToOne
  @JoinColumn(name = "id_empleado")
  private Empleado empleado;

  public Sueldo() {}

  public Empleado getEmpleado() {
    return empleado;
  }

  public void setEmpleado(Empleado empleado) {
    this.empleado = empleado;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Double getMonto() {
    return monto;
  }

  public void setMonto(Double monto) {
    this.monto = monto;
  }

  public Double getComision() {
    return comision;
  }

  public void setComision(Double comision) {
    this.comision = comision;
  }
}
