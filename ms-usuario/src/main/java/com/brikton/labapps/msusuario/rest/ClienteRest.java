package com.brikton.labapps.msusuario.rest;

import com.brikton.labapps.msusuario.domain.Cliente;
import com.brikton.labapps.msusuario.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = {"http://localhost:9005", "http://ms-frontend:9005", "http://localhost:8181", "http://service-gateway:8181"}, maxAge = 3000)
public class ClienteRest {

    protected final Logger logger = LoggerFactory.getLogger(ClienteRest.class);

    private final ClienteService clienteServicio;

    public ClienteRest(ClienteService clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable Integer id) {
        try {
            Cliente cliente = this.clienteServicio.getClienteById(id);
            if(cliente == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen clientes con el id: " + id);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error por lo que no se puede encontrar el cliente.");
        }
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return ResponseEntity.ok(this.clienteServicio.listarClientes());
    }

    @GetMapping(path = "/cuit/{cuit}")
    public ResponseEntity<?> getClienteByCuit(@PathVariable String cuit) {
        try {
            Cliente cliente = this.clienteServicio.getClienteByCuit(cuit);
            if (cliente == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen clientes con el número de CUIT: " + cuit);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error por lo que no se puede encontrar el cliente.");
        }
    }

    @GetMapping(path = "/razonSocial/{razonSocial}")
    public ResponseEntity<?> getClienteByRazonSocial(@PathVariable(required = false) String razonSocial) {
        try {
            if(clienteServicio == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen clientes con la razón social: " + razonSocial);
            return ResponseEntity.ok(this.clienteServicio.getClienteByRazonSocial(razonSocial));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error por lo que no se puede encontrar el cliente.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createCliente(@RequestBody Cliente cliente) {
        try {
            return ResponseEntity.ok(this.clienteServicio.saveCliente(cliente));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error por lo que no se puede crear el cliente.");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateCliente(@RequestBody Cliente cliente) {
        try {
            return ResponseEntity.ok(this.clienteServicio.saveCliente(cliente));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error por lo que no se puede actualizar el cliente.");
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer id) {
        try {
            this.clienteServicio.bajaCliente(id);
            return ResponseEntity.ok().body("El cliente con el id: " + id + " ha sido borrado.");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error por lo que no se puede borrar el cliente.");
        }
    }
}
