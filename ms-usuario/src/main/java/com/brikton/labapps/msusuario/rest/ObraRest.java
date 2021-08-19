package com.brikton.labapps.msusuario.rest;

import com.brikton.labapps.msusuario.domain.Obra;
import com.brikton.labapps.msusuario.domain.TipoObra;
import com.brikton.labapps.msusuario.service.ObraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/obra")
@CrossOrigin(origins = { "http://localhost:9005" }, maxAge = 3000)

public class ObraRest {

    @Autowired
    ObraService obraServicio;

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getObraById(@PathVariable Integer id) {
        try {
            Optional<Obra> obra = this.obraServicio.getObraById(id);
            return ResponseEntity.of(obra);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Obra>> getAllObras() {
        return ResponseEntity.ok(this.obraServicio.getAllObrasByTipo(null));
    }

    @GetMapping(path = "/tipoObra/{tipoObraId}")
    public ResponseEntity<?> getAllObrasByTipo(@PathVariable String tipoObraId) {
        try {
            TipoObra tipoObra = TipoObra.valueOf(tipoObraId);
            return ResponseEntity.ok(this.obraServicio.getAllObrasByTipo(tipoObra));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    "El tipo de obra que se requiere no es válido. \nLos siguientes valores son válidos: \"REFORMA, CASA, EDIFICIO y VIAL\".");
        }
    }

    @PostMapping
    public ResponseEntity<?> saveObra(@RequestBody Obra nueva) {
        try {
            Obra creada = this.obraServicio.saveObra(nueva);
            return ResponseEntity.ok(creada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateObra(@RequestBody Obra nueva, @PathVariable Integer id) {
        try {
            Obra actualizada = this.obraServicio.updateObra(nueva, id);
            return ResponseEntity.ok(actualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<ModelMap> handleError(HttpServletRequest req, Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("reason",
                "El tipo de obra que se requiere no es válido. Los siguientes valores son válidos: REFORMA, CASA, EDIFICIO y VIAL.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mav.getModelMap());
    }

    @GetMapping(path = "/obrasPorCliente/{clienteId}")
    public ResponseEntity<?> getObrasByClienteId(@PathVariable Integer clienteId) {
        try {
            List<Obra> obras = this.obraServicio.getObrasByClienteId(clienteId);
            return ResponseEntity.ok(obras);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(path = "/obrasPorCliente/cuit/{clienteCuit}")
    public ResponseEntity<?> getObrasByClienteCuit(@PathVariable String clienteCuit) {
        try {
            List<Obra> obras = this.obraServicio.getObrasByClienteCuit(clienteCuit);
            return ResponseEntity.ok(obras);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(path = "/saldo/{obraId}")
    public ResponseEntity<?> getSaldoPorObrasByClient(@PathVariable Integer obraId) {
        try {
            Double saldo = this.obraServicio.getSaldoPorObrasByClient(obraId);
            return ResponseEntity.ok(saldo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteObra(@PathVariable Integer id) {
        try {
            obraServicio.deleteObra(id);
            return ResponseEntity.ok().body("La obra con el id: " + id + " ha sido borrada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
