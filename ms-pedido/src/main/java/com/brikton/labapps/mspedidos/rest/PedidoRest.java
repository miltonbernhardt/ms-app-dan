package com.brikton.labapps.mspedidos.rest;

import java.util.ArrayList;
import java.util.List;

import com.brikton.labapps.mspedidos.domain.DetallePedido;
import com.brikton.labapps.mspedidos.domain.EstadoPedido;
import com.brikton.labapps.mspedidos.domain.Obra;
import com.brikton.labapps.mspedidos.domain.Pedido;
import com.brikton.labapps.mspedidos.exception.RecursoNoEncontradoException;
import com.brikton.labapps.mspedidos.exception.RiesgoException;
import com.brikton.labapps.mspedidos.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedido")
@CrossOrigin(origins = { "http://localhost:3000" }, maxAge = 3000)
// @Api(value = "PedidoRest", description = "Permite gestionar los pedidos de la
// empresa")
public class PedidoRest {
    @Autowired
    PedidoService pedidoService;

    @PostMapping
    // @ApiResponses(value = {
    // @ApiResponse(code = 200, message = "Creado correctamente"),
    // @ApiResponse(code = 400, message = "El pedido no es correcto")})
    public ResponseEntity<?> crearPedido(@RequestBody Pedido nuevoPedido) {

        /*
         * Valido que obra, detalle y el detalle tenga productos y cantidad
         */

        Pedido creado = null;
        if (validarPedido(nuevoPedido)) {
            try {
                creado = this.pedidoService.crearPedido(nuevoPedido);
            } catch (RiesgoException e2) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e2.getMessage());
            }
            return ResponseEntity.ok(creado);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    private Boolean validarPedido(Pedido p) {
        Boolean valido = true;
        if (p.getDetalle() != null) {
            for (DetallePedido d : p.getDetalle()) {
                if ((d.getProducto() == null) || (d.getCantidad() == null))
                    valido = false;
            }
        } else {
            valido = false;
        }
        return valido;
    }

    @PutMapping
    public ResponseEntity<?> actualizarEstadoPedido(@RequestParam Integer id, @RequestParam String nuevoEstado) {
        Pedido pedido = null;
        try {
            pedido = pedidoService.actualizarEstadoPedido(id, EstadoPedido.valueOf(nuevoEstado.toUpperCase()));
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RiesgoException e1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e1.getMessage());
        }
        return ResponseEntity.ok(pedido);
    }

    @GetMapping
    public ResponseEntity<?> getPedidos() {
        List<Pedido> pedidos = new ArrayList<>();

        pedidos = pedidoService.getPedidos();

        return ResponseEntity.ok(pedidos);
    }

    @GetMapping(path = "/obra")
    public ResponseEntity<List<Pedido>> pedidosPorObra(@RequestBody Obra obra) {
        ArrayList<Pedido> pedidos;
        pedidos = pedidoService.pedidosPorObra(obra);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping(path = "/estado")
    public ResponseEntity<List<Pedido>> pedidosPorEstado(@RequestParam String estadoPedido) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        pedidos = pedidoService.pedidosPorEstado(EstadoPedido.valueOf(estadoPedido));
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping(path = "/cliente")
    public ResponseEntity<?> pedidosPorCliente(@RequestParam Integer idCliente) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try {
            pedidos = pedidoService.pedidosPorCliente(idCliente);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(pedidos);
    }
}