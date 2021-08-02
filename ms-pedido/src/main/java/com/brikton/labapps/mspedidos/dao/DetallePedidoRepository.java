package com.brikton.labapps.mspedidos.dao;

import com.brikton.labapps.mspedidos.domain.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido,Integer> {} 