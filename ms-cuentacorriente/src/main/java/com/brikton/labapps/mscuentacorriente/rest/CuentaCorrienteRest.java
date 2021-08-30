package com.brikton.labapps.mscuentacorriente.rest;

import com.brikton.labapps.mscuentacorriente.domain.Cliente;
import com.brikton.labapps.mscuentacorriente.domain.Pago;
import com.brikton.labapps.mscuentacorriente.dto.Pedido;
import com.brikton.labapps.mscuentacorriente.service.CuentaCorrienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentacorriente")
@CrossOrigin(origins = {"http://localhost:9005", "http://localhost:8181", "http://service-gateway:8181"}, maxAge = 3000)
public class CuentaCorrienteRest {

    protected final Logger logger = LoggerFactory.getLogger(CuentaCorrienteRest.class);

    private final CuentaCorrienteService cuentaCorrienteService;

    public CuentaCorrienteRest(CuentaCorrienteService cuentaCorrienteService) {
        this.cuentaCorrienteService = cuentaCorrienteService;
    }

    @PostMapping
    public ResponseEntity<?> savePago(@RequestBody Pago pagoNuevo) {
        try {
            return ResponseEntity.ok(this.cuentaCorrienteService.savePago(pagoNuevo));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo registrar el pago.");
        }
    }

    @GetMapping(path = "/pagos")
    public ResponseEntity<?> getPagos(@RequestBody Cliente cliente) {
        try {
            List<Pago> listaPagos = this.cuentaCorrienteService.getDetallePagos(cliente);
            if (listaPagos.size() <= 0)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen pagos registrados por el cliente " + cliente.getRazonSocial());
            return ResponseEntity.ok(listaPagos);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudieron obtener los pagos del cliente.");
        }
    }

    @GetMapping(path = "/factura")
    public ResponseEntity<?> getFactura(@RequestBody Cliente cliente) {
        try {
            List<Pedido> facturas = this.cuentaCorrienteService.getFacturas(cliente);
            if (facturas.size() <= 0)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen facturas registradas por el cliente " + cliente.getRazonSocial());
            return ResponseEntity.ok(facturas);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudieron obtener las facturas.");
        }
    }

}
