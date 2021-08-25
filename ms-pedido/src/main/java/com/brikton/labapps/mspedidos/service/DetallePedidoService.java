package com.brikton.labapps.mspedidos.service;

import com.brikton.labapps.mspedidos.domain.DetallePedido;
import com.brikton.labapps.mspedidos.exception.RecursoNoEncontradoException;

public interface DetallePedidoService {

    DetallePedido addDetalle(DetallePedido detalle, Integer idPedido) throws RecursoNoEncontradoException;

    DetallePedido updateDetalle(DetallePedido detalle) throws RecursoNoEncontradoException;

    void deleteDetalle(DetallePedido detalle) throws RecursoNoEncontradoException;

    void eliminarDetalle(Integer detalleId) throws RecursoNoEncontradoException;
}
