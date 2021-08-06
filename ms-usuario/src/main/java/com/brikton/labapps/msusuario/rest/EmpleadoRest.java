package com.brikton.labapps.msusuario.rest;

import java.util.List;

import com.brikton.labapps.msusuario.domain.Empleado;
import com.brikton.labapps.msusuario.servicioInterfaz.EmpleadoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoRest {
	    
	@Autowired
	EmpleadoServicio empleadoServicio;

	@PostMapping
	public ResponseEntity<Empleado> crear(@RequestBody Empleado nuevo){
		empleadoServicio.crearEmpleado(nuevo);
	    return ResponseEntity.ok(nuevo);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> actualizar(@RequestBody Empleado nuevo, @PathVariable Integer id){
        try{
			empleadoServicio.actualizarEmpleado(nuevo,id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} return ResponseEntity.ok(nuevo);
	}

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> borrar(@PathVariable Integer id){
    	try{
			empleadoServicio.borrarEmpleado(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} return ResponseEntity.ok().build();
	}

	@GetMapping(path="/{id}")
    public ResponseEntity<?> empleadoPorId(@PathVariable Integer id) {
        Empleado emp;
		try{
			emp = empleadoServicio.getEmpleado(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} return ResponseEntity.ok(emp);
    }

	@GetMapping("/nombre")
   public ResponseEntity<?> empleadoPorNombre(@RequestParam String usuario) {
	Empleado emp;
	try{
		emp = empleadoServicio.empleadoPorNombre(usuario);
	} catch (Exception e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	} return ResponseEntity.ok(emp);
    }

	@GetMapping
	public ResponseEntity<List<Empleado>> listaEmpleados(){
		return ResponseEntity.ok(empleadoServicio.listaEmpleados());
	}
}
