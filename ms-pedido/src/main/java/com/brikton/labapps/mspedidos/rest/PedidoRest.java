package com.brikton.labapps.mspedidos.rest;

import java.util.List;

import com.brikton.labapps.mspedidos.domain.DetallePedido;
import com.brikton.labapps.mspedidos.domain.EstadoPedido;
import com.brikton.labapps.mspedidos.domain.Obra;
import com.brikton.labapps.mspedidos.domain.Pedido;
import com.brikton.labapps.mspedidos.exception.RecursoNoEncontradoException;
import com.brikton.labapps.mspedidos.exception.RiesgoException;
import com.brikton.labapps.mspedidos.service.PedidoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedido")
@CrossOrigin(origins = {"http://localhost:9005", "http://localhost:8181", "http://service-gateway:8181"}, maxAge = 3000)
public class PedidoRest {
    protected final Logger logger = LoggerFactory.getLogger(DetallePedidoRest.class);

    private final PedidoService pedidoService;

    public PedidoRest(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<?> crearPedido(@RequestBody Pedido nuevoPedido) {
        if (validarPedido(nuevoPedido)) {
            try {
                return ResponseEntity.ok(this.pedidoService.savePedido(nuevoPedido));
            } catch (RiesgoException e) {
                logger.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Válida que obra, detalle y el detalle tenga productos y cantidad
     */
    private Boolean validarPedido(Pedido p) {
        boolean valido = true;
        if (p.getDetalle() != null) {
            for (DetallePedido d : p.getDetalle()) {
                if ((d.getProducto() == null) || (d.getCantidad() == null)) {
                    valido = false;
                    break;
                }
            }
        } else {
            valido = false;
        }
        return valido;
    }

    @PutMapping
    public ResponseEntity<?> actualizarEstadoPedido(@RequestParam Integer id,
                                                    @RequestParam String nuevoEstado) {
        try {
            return ResponseEntity.ok(pedidoService.updateEstadoPedido(id, EstadoPedido.valueOf(nuevoEstado.toUpperCase())));
        } catch (RecursoNoEncontradoException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RiesgoException e1) {
            logger.error(e1.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e1.getMessage());
        }
    }

    @GetMapping(path = "/")
    public ResponseEntity<?> getAllPedidos() {
        try {
            List<Pedido> pedidos = pedidoService.getPedidos();
            if (pedidos.size() <= 0)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudieron obtener los pedidos.");
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudieron obtener los pedidos.");
        }
    }

    @GetMapping(path = "/obra")
    public ResponseEntity<?> pedidosPorObra(@RequestBody Obra obra) {
        try {
            List<Pedido> pedidos = pedidoService.getPedidosByObra(obra);
            if (pedidos.size() <= 0)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudieron obtener los pedidos.");
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudieron obtener los pedidos.");
        }
    }

    @GetMapping(path = "/estado")
    public ResponseEntity<?> pedidosPorEstado(@RequestParam String estadoPedido) {
        try {
            List<Pedido> pedidos = pedidoService.getPedidosByEstado(EstadoPedido.valueOf(estadoPedido));
            if (pedidos.size() <= 0)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudieron obtener los pedidos.");
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudieron obtener los pedidos.");
        }
    }

    @GetMapping(path = "/cliente")
    public ResponseEntity<?> pedidosPorCliente(@RequestParam Integer idCliente) {
        try {
            return ResponseEntity.ok(pedidoService.getPedidosByCliente(idCliente));
        } catch (RecursoNoEncontradoException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudieron obtener los pedidos del cliente.");
        }
    }
}