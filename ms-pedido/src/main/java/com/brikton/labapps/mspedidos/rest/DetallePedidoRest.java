package com.brikton.labapps.mspedidos.rest;

import com.brikton.labapps.mspedidos.domain.DetallePedido;
import com.brikton.labapps.mspedidos.exception.RecursoNoEncontradoException;
import com.brikton.labapps.mspedidos.service.DetallePedidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/detallepedido")
@CrossOrigin(origins = {"http://localhost:9005", "http://localhost:8181", "http://service-gateway:8181"}, maxAge = 3000)
public class DetallePedidoRest {

    protected final Logger logger = LoggerFactory.getLogger(DetallePedidoRest.class);

    private final DetallePedidoService service;

    public DetallePedidoRest(DetallePedidoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> addDetalle(@RequestBody DetallePedido detalle, @RequestParam Integer idPedido) {
        try {
            detalle = service.addDetalle(detalle, idPedido);
            return ResponseEntity.ok(detalle);
        } catch (RecursoNoEncontradoException e) {
            logger.error(e.getMessage());
            logger.error(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo guardar el item.");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateDetalle(@RequestBody DetallePedido detalle) {
        try {
            return ResponseEntity.ok(service.updateDetalle(detalle));
        } catch (RecursoNoEncontradoException e) {
            logger.error(e.getMessage());
            logger.error(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se puede actualizar el detalle.");
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteDetalle(@RequestBody DetallePedido detalle) {
        try {
            service.deleteDetalle(detalle);
            return ResponseEntity.ok("Detalle eliminado.");
        } catch (RecursoNoEncontradoException e) {
            logger.error(e.getMessage());
            logger.error(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar el detalle.");
        }
    }

    @DeleteMapping(path = "/id")
    public ResponseEntity<?> eliminarDetalleById(@RequestParam Integer idDetalle) {
        try {
            service.eliminarDetalle(idDetalle);
            return ResponseEntity.ok("Detalle eliminado.");
        } catch (RecursoNoEncontradoException e) {
            logger.error(e.getMessage());
            logger.error(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar el detalle.");
        }
    }
}
