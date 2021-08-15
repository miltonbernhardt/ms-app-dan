package com.brikton.labapps.mscuentacorriente.rest;

import com.brikton.labapps.mscuentacorriente.domain.Cliente;
import com.brikton.labapps.mscuentacorriente.domain.Pago;
import com.brikton.labapps.mscuentacorriente.dto.Pedido;
import com.brikton.labapps.mscuentacorriente.service.CuentaCorrienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentacorriente")
public class CuentaCorrienteRest {

    @Autowired
    CuentaCorrienteService cuentaCorrienteService;

    @PostMapping
    public ResponseEntity<?> registrarPago(@RequestBody Pago pagoNuevo) {
        Pago creado;
        try {
            creado = this.cuentaCorrienteService.save(pagoNuevo);
        } catch (Exception e1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e1.getMessage());
        }
        return ResponseEntity.ok(creado);
    }

    @GetMapping(path = "/pagos")
    public ResponseEntity<?> getPagos(@RequestBody Cliente cliente) {
        List<Pago> pagos;
        try {
            pagos = this.cuentaCorrienteService.getDetallePagos(cliente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(pagos);
    }

    @GetMapping(path = "/factura")
    public ResponseEntity<?> getFactura(@RequestBody Cliente cliente) {
        List<Pedido> pagos;
        try {
            pagos = this.cuentaCorrienteService.getFacturas(cliente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(pagos);
    }

}
