package com.brikton.labapps.msusuario.rest;

import com.brikton.labapps.msusuario.domain.Cliente;
import com.brikton.labapps.msusuario.servicioInterfaz.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/cliente")
public class ClienteRest {

    @Autowired
    ClienteServicio clienteServicio;

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> clientePorId(@PathVariable Integer id) {

        Optional<Cliente> cliente = null;
        try {
            cliente = this.clienteServicio.buscarClientePorId(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.of(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> todos() {
        return ResponseEntity.ok(this.clienteServicio.listarClientes());
    }

    @GetMapping(path = "/cuit/{cuit}")
    public ResponseEntity<?> clientePorCuit(@PathVariable String cuit) {
        Optional<Cliente> cliente;
        try {
            cliente = this.clienteServicio.buscarClientePorCuit(cuit);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.of(cliente);
    }

    @GetMapping(path = "/razonSocial/{razonSocial}")
    public ResponseEntity<?> clientePorRazonSocial(@PathVariable(required = false) String razonSocial) {
        Optional<Cliente> cliente = null;
        try {
            cliente = this.clienteServicio.buscarClientePorRazonSocial(razonSocial);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.of(cliente);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Cliente cliente) {
        Cliente creado = null;
        try {
            creado = this.clienteServicio.guardarCliente(cliente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(creado);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Cliente cliente,
                                        @PathVariable Integer id) {
        Cliente actualizado = null;
        try {
            actualizado = this.clienteServicio.guardarCliente(cliente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(actualizado);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> borrar(@PathVariable Integer id) {
        try {
            this.clienteServicio.bajaCliente(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
