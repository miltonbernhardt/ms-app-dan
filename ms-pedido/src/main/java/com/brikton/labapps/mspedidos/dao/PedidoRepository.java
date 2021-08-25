package com.brikton.labapps.mspedidos.dao;

import java.util.List;

import com.brikton.labapps.mspedidos.domain.EstadoPedido;
import com.brikton.labapps.mspedidos.domain.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {

    @Query("select p from Pedido p where p.obra.id = :idObra")
    List<Pedido> getPedidosPorObra(@Param("idObra") Integer idObra);

    @Query("select p from Pedido p where p.estado = :estadoPedido")
    List<Pedido> getPedidosPorEstado(@Param("estadoPedido") EstadoPedido estadoPedido);
}
