package com.brikton.labapps.msliquidacion.domain;

import javax.persistence.*;

@Entity
public class Empleado {

  @Id
  @Column(name = "id_empleado")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  public Empleado() {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
