package com.brikton.labapps.mspedidos.service.implementation;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.brikton.labapps.mspedidos.dao.DetallePedidoRepository;
import com.brikton.labapps.mspedidos.dao.ObraRepository;
import com.brikton.labapps.mspedidos.dao.PedidoRepository;
import com.brikton.labapps.mspedidos.dao.ProductoRepository;
import com.brikton.labapps.mspedidos.domain.DetallePedido;
import com.brikton.labapps.mspedidos.domain.EstadoPedido;
import com.brikton.labapps.mspedidos.domain.Obra;
import com.brikton.labapps.mspedidos.domain.Pedido;
import com.brikton.labapps.mspedidos.domain.Producto;
import com.brikton.labapps.mspedidos.dto.DetallePedidoDTO;
import com.brikton.labapps.mspedidos.exception.RecursoNoEncontradoException;
import com.brikton.labapps.mspedidos.exception.RiesgoException;
import com.brikton.labapps.mspedidos.service.ClienteService;
import com.brikton.labapps.mspedidos.service.PedidoService;
import com.brikton.labapps.mspedidos.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;
	@Autowired
	DetallePedidoRepository detallePedidoRepository;
	@Autowired
	ProductoRepository productoRepository;
	@Autowired
	ObraRepository obraRepository;
    
    @Autowired
	ClienteService clienteSrv;

    @Autowired
    ProductoService productoSrv;

	@Autowired
	JmsTemplate jms;
	
    @Override
    public Pedido crearPedido(Pedido p) throws RiesgoException {

		if (p.getFechaPedido()==null) p.setFechaPedido(Instant.now());

        boolean hayStock = p.getDetalle()
		.stream()
		.allMatch(dp -> verificarStock(dp.getProducto(),dp.getCantidad()));
		
		Double totalOrden = p.getDetalle()
				.stream()
				.mapToDouble( dp -> dp.getCantidad() * dp.getPrecio())
				.sum();
		
		Double saldoCliente = 0.0;
		try {
			saldoCliente = clienteSrv.deudaCliente(p.getObra());
		} catch (RecursoNoEncontradoException e) {
			e.printStackTrace();
		}		
		Double nuevoSaldo = saldoCliente - totalOrden;
		
		Boolean generaDeuda= nuevoSaldo<0;
		if(hayStock ) {
				if(!generaDeuda || (generaDeuda && this.esDeBajoRiesgo(p.getObra(),nuevoSaldo) ))  {
					p.setEstado(EstadoPedido.NUEVO);
				} else {
					throw new RiesgoException("Genera deuda");
				}
		} else {
			p.setEstado(EstadoPedido.PENDIENTE);
		}
		return guardar(p);
    }
	
    @Override
    public Pedido actualizarEstadoPedido(Integer idPedido, EstadoPedido nuevoEstadoPedido) throws RecursoNoEncontradoException, RiesgoException {
        Optional<Pedido> pedido = null;
		pedido = pedidoRepository.findById(idPedido);
		if (pedido.isPresent()) {
			
			Pedido p = pedido.get();
			EstadoPedido e = pedido.get().getEstado();
			
			if (nuevoEstadoPedido == EstadoPedido.CONFIRMADO){
				/*
				Logica de actualizar un pedido confirmado
				*/
				boolean hayStock = p.getDetalle()
				.stream()
				.allMatch(dp -> verificarStock(dp.getProducto(),dp.getCantidad()));
				
				Double totalOrden = p.getDetalle()
				.stream()
				.mapToDouble( dp -> dp.getCantidad() * dp.getPrecio())
				.sum();
				
				Double saldoCliente = clienteSrv.deudaCliente(p.getObra());		
				Double nuevoSaldo = saldoCliente - totalOrden;
				
				Boolean generaDeuda= nuevoSaldo<0;
				if(hayStock ) {
					if(!generaDeuda || (generaDeuda && this.esDeBajoRiesgo(p.getObra(),nuevoSaldo) ))  {
						p.setEstado(EstadoPedido.ACEPTADO);
						ArrayList<DetallePedidoDTO> detalles = new ArrayList<>();
						p.getDetalle().stream().forEach(d -> detalles.add(new DetallePedidoDTO(d)));
						for(DetallePedidoDTO d: detalles)
							try {
								HashMap<String,Integer> map = new HashMap<>();
								map.put("idMaterial", d.getIdMaterial());
								map.put("cantidad",d.getCantidad());
								jms.convertAndSend("COLA_PEDIDOS", map);
							} catch (JmsException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					} else {
						p.setEstado(EstadoPedido.RECHAZADO);
						guardar(p);
						throw new RiesgoException("El pedido" + idPedido +" genera saldo deudor");
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
				|| e == EstadoPedido.PENDIENTE ) {
					p.setEstado(EstadoPedido.CANCELADO);
					return this.guardar(p);
				}
			} else if (nuevoEstadoPedido == EstadoPedido.EN_PREPARACION) {
				/*
				Logica en preparacion
				*/
				if (e == EstadoPedido.CONFIRMADO ) {
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
		}
		else throw new RecursoNoEncontradoException("No se encontro el pedido: ", idPedido);
		return null;
    }
    
	@Override
	public ArrayList<Pedido> pedidosPorObra(Obra obra) {
		// TODO la query solo pasa id de la obra
		ArrayList<Pedido> resultado = new ArrayList<>();
		resultado.addAll(pedidoRepository.getPedidosPorObra(obra.getId()));
		return resultado;
	}
	
	@Override
	public ArrayList<Pedido> pedidosPorEstado(EstadoPedido estadoPedido) {
		// TODO query
		ArrayList<Pedido> resultado = new ArrayList<>();
		resultado.addAll(pedidoRepository.getPedidosPorEstado(estadoPedido));
		return resultado;
	}
	
	@Override
	public ArrayList<Pedido> pedidosPorCliente(Integer idCliente) throws RecursoNoEncontradoException {
		// TODO esto usa lo que hay que ver con tamara
		/*
		Pido obras segun idCliente y despues pido los pedidos segun las obras
		*/
		List<Obra> obras = clienteSrv.getObrasCliente(idCliente);
		ArrayList<Pedido> pedidos = new ArrayList<>();
		for(Obra o: obras) pedidos.addAll(pedidoRepository.getPedidosPorObra(o.getId()));
		return pedidos;
	}
	
	@Override
	public Pedido getPedido(Integer idPedido) throws RecursoNoEncontradoException {
		Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
		if (pedido.isPresent()) return pedido.get();
		else throw new RecursoNoEncontradoException("No se encontro el pedido: ", idPedido);
	}
	
	@Override
	public void actualizarPedido(Pedido p){
		guardar(p);
	}
	
	private boolean esDeBajoRiesgo(Obra obra, Double nuevoSaldo) {
		//TODO riesgo bcra
		return false;
	}
	
	private Boolean verificarStock(Producto producto, Integer cantidad) {
		try {
			return productoSrv.stockDisponible(producto)>=cantidad;
		} catch (RecursoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Pedido guardar(Pedido p){
		obraRepository.save(p.getObra());
		for (DetallePedido d: p.getDetalle()) {
			productoRepository.save(d.getProducto());
			detallePedidoRepository.save(d);
		}
		return this.pedidoRepository.save(p);
	}
}
