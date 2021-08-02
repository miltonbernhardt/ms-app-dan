package com.brikton.labapps.msusuario.rest;

import com.brikton.labapps.msusuario.domain.Obra;
import com.brikton.labapps.msusuario.domain.TipoObra;
import com.brikton.labapps.msusuario.servicioInterfaz.ObraServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/obra")
public class ObraRest {

    @Autowired
    ObraServicio obraServicio;

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> obraPorId(@PathVariable Integer id) {
        Optional<Obra> obra;
        try {
            obra = this.obraServicio.buscarObraPorId(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.of(obra);
    }

    @GetMapping
    public ResponseEntity<List<Obra>> todos() {
        return ResponseEntity.ok(this.obraServicio.listarObras(null));
    }

    @GetMapping(path = "/tipoObra/{tipoObraId}")
    public ResponseEntity<?> todosFiltrado(@PathVariable String tipoObraId) {
        TipoObra tipoObra;
        try {
            tipoObra = TipoObra.valueOf(tipoObraId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("El tipo de obra que se requiere no es válido. \nLos siguientes valores son válidos: \"REFORMA, CASA, EDIFICIO y VIAL\".");
        }
        return ResponseEntity.ok(this.obraServicio.listarObras(tipoObra));
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Obra nueva) {
        Obra creada;
        try {
            creada = this.obraServicio.guardarObra(nueva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(creada);
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody Obra nueva) {
        Obra actualizada;
        try {
            actualizada = this.obraServicio.guardarObra(nueva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(actualizada);
    }

    @GetMapping(path = "/obrasPorCliente/{clienteId}")
    public ResponseEntity<?> obrasPorCliente(@PathVariable Integer clienteId) {
        List<Obra> obras;
        try {
            obras = this.obraServicio.listarObrasPorCliente(clienteId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(obras);
    }

    @GetMapping(path = "/obrasPorCliente/cuit/{clienteCuit}")
    public ResponseEntity<?> obrasPorClienteCuit(@PathVariable String clienteCuit) {
        List<Obra> obras;
        try {
            obras = this.obraServicio.listarObrasPorCliente(clienteCuit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(obras);
    }

    @GetMapping(path = "/saldo/{obraId}")
    public ResponseEntity<?> saldoClienteDeObra(@PathVariable Integer obraId) {
        Double saldo;
        try {
            saldo = this.obraServicio.buscarSaldoClienteDeObra(obraId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(saldo);
    }
}
