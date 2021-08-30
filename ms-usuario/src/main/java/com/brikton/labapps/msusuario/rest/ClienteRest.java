package com.brikton.labapps.msusuario.rest;

import com.brikton.labapps.msusuario.domain.Cliente;
import com.brikton.labapps.msusuario.exceptions.ClienteNoEncontradoException;
import com.brikton.labapps.msusuario.exceptions.UsuarioInvalidoException;
import com.brikton.labapps.msusuario.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = {"http://localhost:9005", "http://localhost:8181", "http://service-gateway:8181"}, maxAge = 3000)
public class ClienteRest {

    protected final Logger logger = LoggerFactory.getLogger(ClienteRest.class);

    private final ClienteService clienteServicio;

    public ClienteRest(ClienteService clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(this.clienteServicio.getClienteById(id));
        } catch (ClienteNoEncontradoException e) {
            logger.error(e.getMessage());
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
            return ResponseEntity.ok(this.clienteServicio.getClienteByCuit(cuit));
        } catch (ClienteNoEncontradoException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(path = "/razonSocial/{razonSocial}")
    public ResponseEntity<?> getClienteByRazonSocial(@PathVariable(required = false) String razonSocial) {
        try {
            return ResponseEntity.ok(this.clienteServicio.getClienteByRazonSocial(razonSocial));
        } catch (ClienteNoEncontradoException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createCliente(@RequestBody Cliente cliente) {
        try {
            return ResponseEntity.ok(this.clienteServicio.saveCliente(cliente));
        } catch (ClienteNoEncontradoException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UsuarioInvalidoException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateCliente(@RequestBody Cliente cliente) {
        try {
            return ResponseEntity.ok(this.clienteServicio.updateCliente(cliente));
        } catch (ClienteNoEncontradoException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UsuarioInvalidoException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer id) {
        try {
            this.clienteServicio.bajaCliente(id);
            return ResponseEntity.ok().body("El cliente con el id: " + id + " ha sido borrado.");
        } catch (ClienteNoEncontradoException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
