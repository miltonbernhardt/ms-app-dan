package com.brikton.labapps.msusuario.rest;

import com.brikton.labapps.msusuario.domain.Cliente;
import com.brikton.labapps.msusuario.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteRest {

    private ClienteService clienteServicio;

    public ClienteRest(ClienteService clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable Integer id) {
        try {
            return ResponseEntity.of(this.clienteServicio.getClienteById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return ResponseEntity.ok(this.clienteServicio.listarClientes());
    }

    @GetMapping(path = "/cuit/{cuit}")
    public ResponseEntity<?> getClienteByCuit(@PathVariable String cuit) {
        try {
            return ResponseEntity.of(this.clienteServicio.getClienteByCuit(cuit));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(path = "/razonSocial/{razonSocial}")
    public ResponseEntity<?> getClienteByRazonSocial(@PathVariable(required = false) String razonSocial) {
        try {
            return ResponseEntity.of(this.clienteServicio.getClienteByRazonSocial(razonSocial));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createCliente(@RequestBody Cliente cliente) {
        try {
            return ResponseEntity.ok(this.clienteServicio.saveCliente(cliente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateCliente(@RequestBody Cliente cliente) {
        try {
            return ResponseEntity.ok(this.clienteServicio.saveCliente(cliente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer id) {
        try {
            this.clienteServicio.bajaCliente(id);
            return ResponseEntity.ok().body("El cliente con el id: " + id + " ha sido borrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
