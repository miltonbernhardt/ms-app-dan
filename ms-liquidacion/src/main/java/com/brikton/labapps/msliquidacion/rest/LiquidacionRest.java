package com.brikton.labapps.msliquidacion.rest;

import java.util.List;

import com.brikton.labapps.msliquidacion.domain.LiquidacionSueldo;
import com.brikton.labapps.msliquidacion.domain.Sueldo;
import com.brikton.labapps.msliquidacion.domain.Venta;
import com.brikton.labapps.msliquidacion.service.LiquidacionService;
import com.brikton.labapps.msliquidacion.service.VentaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/liquidacion")
@CrossOrigin(origins = {"http://localhost:9005", "http://localhost:8181", "http://service-gateway:8181"}, maxAge = 3000)
public class LiquidacionRest {

    protected final Logger logger = LoggerFactory.getLogger(LiquidacionRest.class);

    private final LiquidacionService liquidacionService;
    private final VentaService ventaService;

    public LiquidacionRest(LiquidacionService liquidacionService, VentaService ventaService) {
        this.liquidacionService = liquidacionService;
        this.ventaService = ventaService;
    }

    @PostMapping(path = "/todos")
    public ResponseEntity<?> liquidarSueldoTodos() {
        return ResponseEntity.ok(liquidacionService.liquidarSueldoTodos());
    }

    @PostMapping(path = "/empleado")
    public ResponseEntity<?> liquidarSueldoEmpleado(@RequestParam Integer idEmpleado) {
        try {
            return ResponseEntity.ok(liquidacionService.liquidarSueldoEmpleado(idEmpleado));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo liquidar el sueldo del empleado.");
        }

    }

    @GetMapping("/comisiones")
    public ResponseEntity<?> getComisiones(@RequestParam Integer idEmpleado) {
        try {
            return ResponseEntity.ok(liquidacionService.getComisiones(idEmpleado));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo obtener las comisiones.");
        }
    }

    @GetMapping
    public ResponseEntity<?> getLiquidaciones() {
        return ResponseEntity.ok(liquidacionService.getLiquidaciones());
    }

    @PostMapping(path = "/empleado/sueldo")
    public ResponseEntity<?> actualizarSueldoEmpleado(@RequestBody Sueldo sueldo) {
        try {
            liquidacionService.actualizarSueldoEmpleado(sueldo);
            return ResponseEntity.ok("Sueldo correctamente actualizado");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo actualizar el sueldo del empleado.");
        }
    }

    @GetMapping(path = "/empleado/sueldo")
    public ResponseEntity<?> getSueldoEmpleado(@RequestParam Integer idEmpleado) {
        try {
            return ResponseEntity.ok(liquidacionService.getSueldoEmpleado(idEmpleado));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo obtener el sueldo del empleado.");
        }
    }

    /*
     * Dirección para registrar ventas y poder probar el ms
     */
    @PostMapping("/ventas")
    public ResponseEntity<?> registrarVentas(@RequestBody List<Venta> ventas) {
        try {
            return ResponseEntity.ok(ventaService.registrarVentas(ventas));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo registrar las ventas.");
        }
    }
}
