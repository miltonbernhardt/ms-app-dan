package com.brikton.labapps.mspedidos.service;

import java.util.ArrayList;
import java.util.List;

import com.brikton.labapps.mspedidos.domain.EstadoPedido;
import com.brikton.labapps.mspedidos.domain.Obra;
import com.brikton.labapps.mspedidos.domain.Pedido;
import com.brikton.labapps.mspedidos.exception.RecursoNoEncontradoException;
import com.brikton.labapps.mspedidos.exception.RiesgoException;

public interface PedidoService {

    Pedido savePedido(Pedido p) throws RiesgoException, RecursoNoEncontradoException;

    Pedido updateEstadoPedido(Integer id, EstadoPedido estadoPedido) throws RecursoNoEncontradoException, RiesgoException;

    Pedido getPedido(Integer idPedido) throws RecursoNoEncontradoException;

    void updatePedido(Pedido p);

    ArrayList<Pedido> getPedidosByObra(Obra obra);

    ArrayList<Pedido> getPedidosByEstado(EstadoPedido estadoPedidos);

    ArrayList<Pedido> getPedidosByCliente(Integer idCliente) throws RecursoNoEncontradoException;

    List<Pedido> getPedidos();
}
