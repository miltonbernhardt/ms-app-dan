package com.brikton.labapps.msusuario.rest;

import com.brikton.labapps.msusuario.domain.Empleado;
import com.brikton.labapps.msusuario.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoRest {

    @Autowired
    EmpleadoService empleadoServicio;

    @PostMapping
    public ResponseEntity<Empleado> createVendedor(@RequestBody Empleado nuevo) {
        empleadoServicio.createEmpleado(nuevo);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateEmpleado(@RequestBody Empleado nuevo, @PathVariable Integer id) {
        try {
            empleadoServicio.updateEmpleado(nuevo, id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(nuevo);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteEmpleado(@PathVariable Integer id) {
        try {
            empleadoServicio.deleteEmpleado(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok().body("El empleado con el id: " + id + " ha sido borrado.");
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getEmpleadoById(@PathVariable Integer id) {
        Empleado empleado;
        try {
            empleado = empleadoServicio.getEmpleado(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(empleado);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> getEmpleadoByNombre(@PathVariable String nombre) {
        Optional<Empleado> empleado;
        try {
            empleado = empleadoServicio.getEmpleadoByNombre(nombre);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.of(empleado);
    }

    @GetMapping
    public ResponseEntity<List<Empleado>> getAllEmpleados() {
        return ResponseEntity.ok(empleadoServicio.getAllEmpleados());
    }
}
