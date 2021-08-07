package com.brikton.labapps.msliquidacion.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pedido {

  @Id
  @Column(name = "id_pedido")
  private Integer id;
}
