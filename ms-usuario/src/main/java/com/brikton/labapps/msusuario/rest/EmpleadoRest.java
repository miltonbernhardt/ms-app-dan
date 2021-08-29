package com.brikton.labapps.msusuario.rest;

import com.brikton.labapps.msusuario.domain.Empleado;
import com.brikton.labapps.msusuario.exceptions.EmpleadoNoEncontradoException;
import com.brikton.labapps.msusuario.exceptions.UsuarioInvalidoException;
import com.brikton.labapps.msusuario.service.EmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleado")
@CrossOrigin(origins = {"http://localhost:9005", "http://ms-frontend:9005", "http://localhost:8181", "http://service-gateway:8181"}, maxAge = 3000)
public class EmpleadoRest {

    protected final Logger logger = LoggerFactory.getLogger(EmpleadoRest.class);

    private final EmpleadoService empleadoServicio;

    public EmpleadoRest(EmpleadoService empleadoServicio) {
        this.empleadoServicio = empleadoServicio;
    }

    @PostMapping
    public ResponseEntity<?> createVendedor(@RequestBody Empleado empleado) {
        try {
            return ResponseEntity.ok(empleadoServicio.saveEmpleado(empleado));
        } catch (UsuarioInvalidoException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateEmpleado(@RequestBody Empleado empleado, @PathVariable Integer id) {
        try {
            return ResponseEntity.ok(empleadoServicio.updateEmpleado(empleado, id));
        } catch (EmpleadoNoEncontradoException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteEmpleado(@PathVariable Integer id) {
        try {
            empleadoServicio.deleteEmpleado(id);
            return ResponseEntity.ok().body("El empleado con el id: " + id + " ha sido borrado.");
        }catch (EmpleadoNoEncontradoException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getEmpleadoById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(empleadoServicio.getEmpleado(id));
        } catch (EmpleadoNoEncontradoException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> getEmpleadoByNombre(@PathVariable String nombre) {
        try {
            return ResponseEntity.ok(empleadoServicio.getEmpleadoByNombre(nombre));
        } catch (EmpleadoNoEncontradoException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Empleado>> getAllEmpleados() {
        return ResponseEntity.ok(empleadoServicio.getAllEmpleados());
    }
}
