package com.brikton.labapps.msusuario.rest;

import com.brikton.labapps.msusuario.domain.Obra;
import com.brikton.labapps.msusuario.domain.TipoObra;
import com.brikton.labapps.msusuario.service.ObraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/obra")
public class ObraRest {

    protected final Logger logger = LoggerFactory.getLogger(ObraRest.class);

    private final ObraService obraServicio;

    public ObraRest(ObraService obraServicio) {
        this.obraServicio = obraServicio;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getObraById(@PathVariable Integer id) {
        try {
            return ResponseEntity.of(this.obraServicio.getObraById(id));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo obtener la obra debido a un error interno.");
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
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("El tipo de obra que se requiere no es válido. \nLos siguientes valores son válidos: \"REFORMA, CASA, EDIFICIO y VIAL\".");
        }
    }

    @PostMapping
    public ResponseEntity<?> saveObra(@RequestBody Obra nueva) {
        try {
            return ResponseEntity.ok(this.obraServicio.saveObra(nueva));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo guardar la obra debido a un error interno.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateObra(@RequestBody Obra nueva, @PathVariable Integer id) {
        try {
            Obra actualizada = this.obraServicio.updateObra(nueva, id);
            return ResponseEntity.ok(actualizada);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo actualizar la obra debido a un error interno.");
        }
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<ModelMap> handleError(HttpServletRequest req, Exception ex) {
        logger.error(ex.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("reason", "El tipo de obra que se requiere no es válido. Los siguientes valores son válidos: REFORMA, CASA, EDIFICIO y VIAL.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mav.getModelMap());
    }

    @GetMapping(path = "/obrasPorCliente/{clienteId}")
    public ResponseEntity<?> getObrasByClienteId(@PathVariable Integer clienteId) {
        try {
            List<Obra> obras = this.obraServicio.getObrasByClienteId(clienteId);
            return ResponseEntity.ok(obras);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo obtener las obras debido a un error interno.");
        }
    }

    @GetMapping(path = "/obrasPorCliente/cuit/{clienteCuit}")
    public ResponseEntity<?> getObrasByClienteCuit(@PathVariable String clienteCuit) {
        try {
            List<Obra> obras = this.obraServicio.getObrasByClienteCuit(clienteCuit);
            return ResponseEntity.ok(obras);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo obtener las obras debido a un error interno.");
        }
    }

    @GetMapping(path = "/saldo/{obraId}")
    public ResponseEntity<?> getSaldoPorObrasByClient(@PathVariable Integer obraId) {
        try {
            Double saldo = this.obraServicio.getSaldoPorObrasByClient(obraId);
            return ResponseEntity.ok(saldo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo obtener el saldo por obras debido a un error interno.");
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteObra(@PathVariable Integer id) {
        try {
            obraServicio.deleteObra(id);
            return ResponseEntity.ok().body("La obra con el id: " + id + " ha sido borrada");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo borrar la obra debido a un error interno.");
        }
    }
}
