package com.brikton.labapps.mspedidos.service;

import com.brikton.labapps.mspedidos.domain.DetallePedido;
import com.brikton.labapps.mspedidos.exception.RecursoNoEncontradoException;

public interface DetallePedidoService {

    public DetallePedido agregarDetalle(DetallePedido detalle, Integer idPedido) throws RecursoNoEncontradoException;

    public DetallePedido actualizarDetalle(DetallePedido detalle) throws RecursoNoEncontradoException;

    public void eliminarDetalle(DetallePedido detalle) throws RecursoNoEncontradoException;

    public void eliminarDetalle(Integer detalleId) throws RecursoNoEncontradoException;

}
