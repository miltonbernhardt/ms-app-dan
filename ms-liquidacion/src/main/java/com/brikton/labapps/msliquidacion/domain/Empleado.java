package com.brikton.labapps.msliquidacion.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Empleado {

  @Id
  private Integer id;

  public Empleado() {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
