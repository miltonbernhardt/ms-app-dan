package com.brikton.labapps.mspedidos.service.implementation;

import com.brikton.labapps.mspedidos.dao.DetallePedidoRepository;
import com.brikton.labapps.mspedidos.dao.ObraRepository;
import com.brikton.labapps.mspedidos.dao.PedidoRepository;
import com.brikton.labapps.mspedidos.dao.ProductoRepository;
import com.brikton.labapps.mspedidos.domain.*;
import com.brikton.labapps.mspedidos.dto.DetallePedidoDTO;
import com.brikton.labapps.mspedidos.exception.RecursoNoEncontradoException;
import com.brikton.labapps.mspedidos.exception.RiesgoException;
import com.brikton.labapps.mspedidos.service.ClienteService;
import com.brikton.labapps.mspedidos.service.PedidoService;
import com.brikton.labapps.mspedidos.service.ProductoService;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    private final ProductoRepository productoRepository;
    private final ObraRepository obraRepository;
    private final ClienteService clienteSrv;
    private final ProductoService productoSrv;
    private final JmsTemplate jms;

    public PedidoServiceImpl(
            PedidoRepository pedidoRepository,
            DetallePedidoRepository detallePedidoRepository,
            ProductoRepository productoRepository,
            ObraRepository obraRepository,
            ClienteService clienteSrv,
            ProductoService productoSrv,
            JmsTemplate jms
    ) {
        this.pedidoRepository = pedidoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
        this.productoRepository = productoRepository;
        this.obraRepository = obraRepository;
        this.clienteSrv = clienteSrv;
        this.productoSrv = productoSrv;
        this.jms = jms;
    }

    @Override
    public Pedido savePedido(Pedido p) throws RiesgoException, RecursoNoEncontradoException {

        if (p.getFechaPedido() == null) p.setFechaPedido(LocalDate.now());

        boolean hayStock = true;
        for (DetallePedido detallePedido : p.getDetalle()) {
            if (!verificarStock(detallePedido.getProducto(), detallePedido.getCantidad())) {
                hayStock = false;
                break;
            }
        }

        Double totalOrden = p.getDetalle()
                .stream()
                .mapToDouble(dp -> dp.getCantidad() * dp.getPrecio())
                .sum();

        Double saldoCliente = 0.0;
        try {
            saldoCliente = clienteSrv.deudaCliente(p.getObra());
        } catch (RecursoNoEncontradoException e) {
            e.printStackTrace();
        }
        double nuevoSaldo = saldoCliente - totalOrden;

        boolean generaDeuda = nuevoSaldo < 0;
        if (hayStock) {
            if (!generaDeuda || this.esDeBajoRiesgo(p.getObra(), nuevoSaldo)) {
                p.setEstado(EstadoPedido.NUEVO);
            } else {
                throw new RiesgoException("El pedido fenera deuda");
            }
        } else {
            p.setEstado(EstadoPedido.PENDIENTE);
        }
        return guardar(p);
    }

    @Override
    public Pedido updateEstadoPedido(Integer idPedido, EstadoPedido nuevoEstadoPedido) throws RecursoNoEncontradoException, RiesgoException {
        Optional<Pedido> pedido;
        pedido = pedidoRepository.findById(idPedido);
        if (pedido.isPresent()) {

            Pedido p = pedido.get();
            EstadoPedido e = pedido.get().getEstado();

            if (nuevoEstadoPedido == EstadoPedido.CONFIRMADO) {
                //Lógica de actualizar un pedido confirmado
                boolean hayStock = true;
                for (DetallePedido detallePedido : p.getDetalle()) {
                    if (!verificarStock(detallePedido.getProducto(), detallePedido.getCantidad())) {
                        hayStock = false;
                        break;
                    }
                }

                Double totalOrden = p.getDetalle()
                        .stream()
                        .mapToDouble(dp -> dp.getCantidad() * dp.getPrecio())
                        .sum();

                Double saldoCliente = clienteSrv.deudaCliente(p.getObra());
                double nuevoSaldo = saldoCliente - totalOrden;

                boolean generaDeuda = nuevoSaldo < 0;
                if (hayStock) {
                    if (!generaDeuda || this.esDeBajoRiesgo(p.getObra(), nuevoSaldo)) {
                        p.setEstado(EstadoPedido.ACEPTADO);
                        ArrayList<DetallePedidoDTO> detalles = new ArrayList<>();
                        p.getDetalle().forEach(d -> detalles.add(new DetallePedidoDTO(d)));
                        for (DetallePedidoDTO d : detalles)
                            try {
                                HashMap<String, Integer> map = new HashMap<>();
                                map.put("idMaterial", d.getIdMaterial());
                                map.put("cantidad", d.getCantidad());
                                jms.convertAndSend("COLA_PEDIDOS", map);
                            } catch (JmsException e1) {
                                e1.printStackTrace();
                            }
                    } else {
                        p.setEstado(EstadoPedido.RECHAZADO);
                        guardar(p);
                        throw new RiesgoException("El pedido" + idPedido + " genera saldo deudor");
                    }
                } else {
                    p.setEstado(EstadoPedido.PENDIENTE);
                }
                return this.guardar(p);
            } else if (nuevoEstadoPedido == EstadoPedido.CANCELADO) {
				/*
				Logica de cancelar pedido
				*/
                if (e == EstadoPedido.NUEVO || e == EstadoPedido.CONFIRMADO
                        || e == EstadoPedido.PENDIENTE) {
                    p.setEstado(EstadoPedido.CANCELADO);
                    return this.guardar(p);
                }
            } else if (nuevoEstadoPedido == EstadoPedido.EN_PREPARACION) {
				/*
				Logica en preparacion
				*/
                if (e == EstadoPedido.CONFIRMADO) {
                    p.setEstado(EstadoPedido.EN_PREPARACION);
                    return this.guardar(p);
                }
            } else if (nuevoEstadoPedido == EstadoPedido.ENTREGADO) {
				/*
				Logica entregado
				*/
                if (e == EstadoPedido.EN_PREPARACION) {
                    p.setEstado(EstadoPedido.ENTREGADO);
                    return this.guardar(p);
                }
            }
        } else throw new RecursoNoEncontradoException("No se encontró el pedido: ", idPedido);
        return null;
    }

    @Override
    public ArrayList<Pedido> getPedidosByObra(Obra obra) {
        return new ArrayList<>(pedidoRepository.getPedidosPorObra(obra.getId()));
    }

    @Override
    public ArrayList<Pedido> getPedidosByEstado(EstadoPedido estadoPedido) {
        return new ArrayList<>(pedidoRepository.getPedidosPorEstado(estadoPedido));
    }

    @Override
    public ArrayList<Pedido> getPedidosByCliente(Integer idCliente) throws RecursoNoEncontradoException {
        List<Obra> obras = clienteSrv.getObrasCliente(idCliente);
        ArrayList<Pedido> pedidos = new ArrayList<>();
        for (Obra o : obras) pedidos.addAll(pedidoRepository.getPedidosPorObra(o.getId()));
        return pedidos;
    }

    @Override
    public Pedido getPedido(Integer idPedido) throws RecursoNoEncontradoException {
        Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
        if (pedido.isPresent()) return pedido.get();
        else throw new RecursoNoEncontradoException("No se encontró el pedido: ", idPedido);
    }

    @Override
    public void updatePedido(Pedido p) {
        guardar(p);
    }

    private boolean esDeBajoRiesgo(Obra obra, Double nuevoSaldo) {
        //TODO riesgo bcra
        return false;
    }

    private Boolean verificarStock(Producto producto, Integer cantidad) throws RecursoNoEncontradoException {
        return productoSrv.getStockDisponible(producto) >= cantidad;
    }

    private Pedido guardar(Pedido p) {
        obraRepository.save(p.getObra());
        for (DetallePedido d : p.getDetalle()) {
            productoRepository.save(d.getProducto());
            detallePedidoRepository.save(d);
        }
        return this.pedidoRepository.save(p);
    }

    @Override
    public List<Pedido> getPedidos() {
        return pedidoRepository.findAll();
    }
}
