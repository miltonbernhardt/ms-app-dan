package com.brikton.labapps.msusuario.rest;

import com.brikton.labapps.msusuario.domain.Empleado;
import com.brikton.labapps.msusuario.service.EmpleadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoRest {

    private EmpleadoService empleadoServicio;

    public EmpleadoRest(EmpleadoService empleadoServicio) {
        this.empleadoServicio = empleadoServicio;
    }

    @PostMapping
    public ResponseEntity<?> createVendedor(@RequestBody Empleado empleado) {
        try {
            return ResponseEntity.ok(empleadoServicio.saveEmpleado(empleado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateEmpleado(@RequestBody Empleado empleado, @PathVariable Integer id) {
        try {
            return ResponseEntity.ok(empleadoServicio.updateEmpleado(empleado, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteEmpleado(@PathVariable Integer id) {
        try {
            empleadoServicio.deleteEmpleado(id);
            return ResponseEntity.ok().body("El empleado con el id: " + id + " ha sido borrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getEmpleadoById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(empleadoServicio.getEmpleado(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> getEmpleadoByNombre(@PathVariable String nombre) {
        try {
            return ResponseEntity.of(empleadoServicio.getEmpleadoByNombre(nombre));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Empleado>> getAllEmpleados() {
        return ResponseEntity.ok(empleadoServicio.getAllEmpleados());
    }
}
