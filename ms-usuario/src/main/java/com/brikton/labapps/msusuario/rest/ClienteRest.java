package com.brikton.labapps.msusuario.rest;

import com.brikton.labapps.msusuario.domain.Cliente;
import com.brikton.labapps.msusuario.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = { "http://localhost:9005" }, maxAge = 3000)

public class ClienteRest {

    @Autowired
    ClienteService clienteServicio;

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente;
        try {
            cliente = this.clienteServicio.buscarClientePorId(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.of(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return ResponseEntity.ok(this.clienteServicio.listarClientes());
    }

    @GetMapping(path = "/cuit/{cuit}")
    public ResponseEntity<?> getClienteByCuit(@PathVariable String cuit) {
        Optional<Cliente> cliente;
        try {
            cliente = this.clienteServicio.buscarClientePorCuit(cuit);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.of(cliente);
    }

    @GetMapping(path = "/razonSocial/{razonSocial}")
    public ResponseEntity<?> getClienteByRazonSocial(@PathVariable(required = false) String razonSocial) {
        Optional<Cliente> cliente;
        try {
            cliente = this.clienteServicio.buscarClientePorRazonSocial(razonSocial);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.of(cliente);
    }

    @PostMapping
    public ResponseEntity<?> createCliente(@RequestBody Cliente cliente) {
        Cliente creado;
        try {
            creado = this.clienteServicio.guardarCliente(cliente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(creado);
    }

    @PutMapping
    public ResponseEntity<?> updateCliente(@RequestBody Cliente cliente) {
        Cliente actualizado;
        try {
            actualizado = this.clienteServicio.guardarCliente(cliente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer id) {
        try {
            this.clienteServicio.bajaCliente(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok().body("El cliente con el id: " + id + " ha sido borrado.");
    }
}
