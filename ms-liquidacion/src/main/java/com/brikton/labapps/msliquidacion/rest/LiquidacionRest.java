package com.brikton.labapps.msliquidacion.rest;

import java.util.List;

import com.brikton.labapps.msliquidacion.domain.LiquidacionSueldo;
import com.brikton.labapps.msliquidacion.domain.Sueldo;
import com.brikton.labapps.msliquidacion.domain.Venta;
import com.brikton.labapps.msliquidacion.service.LiquidacionService;
import com.brikton.labapps.msliquidacion.service.VentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/liquidacion")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3000)
public class LiquidacionRest {

  @Autowired
  LiquidacionService liquidacionService;

  @Autowired
  VentaService ventaService;

  @PostMapping(path = "/todos")
  public ResponseEntity<?> liquidarSueldoTodos() {
    List<LiquidacionSueldo> liquidaciones = liquidacionService.liquidarSueldoTodos();
    return ResponseEntity.ok(liquidaciones);
  }

  @PostMapping(path = "/empleado")
  public ResponseEntity<?> liquidarSueldoEmpleado(@RequestParam Integer idEmpleado) {
    LiquidacionSueldo ls;
    try {
      ls = liquidacionService.liquidarSueldoEmpleado(idEmpleado);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    return ResponseEntity.ok(ls);
  }

  @GetMapping("/comisiones")
  public ResponseEntity<?> getComisiones(@RequestParam Integer idEmpleado) {
    Double comisiones = 0d;
    try {
      comisiones = liquidacionService.getComisiones(idEmpleado);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    return ResponseEntity.ok(comisiones);
  }

  @GetMapping
  public ResponseEntity<?> getLiquidaciones() {
    List<LiquidacionSueldo> listaLiquidaciones = liquidacionService.getLiquidaciones();

    return ResponseEntity.ok(listaLiquidaciones);
  }

  @PostMapping(path = "/empleado/sueldo")
  public ResponseEntity<?> actualizarSueldoEmpleado(@RequestBody Sueldo sueldo) {
    try {
      liquidacionService.actualizarSueldoEmpleado(sueldo);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    return ResponseEntity.ok().build();
  }

  @GetMapping(path = "/empleado/sueldo")
  public ResponseEntity<?> getSueldoEmpleado(@RequestParam Integer idEmpleado) {
    Sueldo sueldo;
    try {
      sueldo = liquidacionService.getSueldoEmpleado(idEmpleado);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    return ResponseEntity.ok(sueldo);
  }

  /*
   * Direccion para registrar ventas y poder probar el ms
   */
  @PostMapping("/ventas")
  public ResponseEntity<?> registrarVentas(@RequestBody List<Venta> ventas) {
    List<Venta> registradas;
    try {
      registradas = ventaService.registrarVentas(ventas);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    return ResponseEntity.ok(registradas);
  }
}
