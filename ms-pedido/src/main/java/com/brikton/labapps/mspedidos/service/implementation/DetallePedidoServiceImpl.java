package com.brikton.labapps.mspedidos.service.implementation;

import com.brikton.labapps.mspedidos.dao.DetallePedidoRepository;
import com.brikton.labapps.mspedidos.dao.ProductoRepository;
import com.brikton.labapps.mspedidos.domain.DetallePedido;
import com.brikton.labapps.mspedidos.domain.Pedido;
import com.brikton.labapps.mspedidos.exception.RecursoNoEncontradoException;
import com.brikton.labapps.mspedidos.service.DetallePedidoService;
import com.brikton.labapps.mspedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;
    private final ProductoRepository productoRepository;
    private final PedidoService pedidoService;

    @Autowired
    public DetallePedidoServiceImpl(DetallePedidoRepository detallePedidoRepository,
            ProductoRepository productoRepository, PedidoService pedidoService) {
        this.detallePedidoRepository = detallePedidoRepository;
        this.productoRepository = productoRepository;
        this.pedidoService = pedidoService;

    }

    @Override
    public DetallePedido addDetalle(DetallePedido detalle, Integer idPedido) throws RecursoNoEncontradoException {
        Pedido pedido = pedidoService.getPedido(idPedido);

        productoRepository.save(detalle.getProducto());
        detallePedidoRepository.save(detalle);

        pedido.agregarDetalle(detalle);
        pedidoService.updatePedido(pedido);
        return detalle;
    }

    @Override
    public DetallePedido updateDetalle(DetallePedido detalle) throws RecursoNoEncontradoException {
        if (detallePedidoRepository.existsById(detalle.getId())) {

            productoRepository.save(detalle.getProducto());
            return detallePedidoRepository.save(detalle);

        } else {
            throw new RecursoNoEncontradoException("Detalle de pedido no encontrado: ", detalle.getId());
        }
    }

    @Override
    public void deleteDetalle(DetallePedido detalle) throws RecursoNoEncontradoException {
        if (detallePedidoRepository.existsById(detalle.getId())) {
            detallePedidoRepository.delete(detalle);
        } else {
            throw new RecursoNoEncontradoException("Detalle de pedido no encontrado: ", detalle.getId());
        }
    }

    @Override
    public void eliminarDetalle(Integer detalleId) throws RecursoNoEncontradoException {
        if (detallePedidoRepository.existsById(detalleId)) {
            detallePedidoRepository.deleteById(detalleId);
        } else {
            throw new RecursoNoEncontradoException("Detalle de pedido no encontrado: ", detalleId);
        }
    }

}
