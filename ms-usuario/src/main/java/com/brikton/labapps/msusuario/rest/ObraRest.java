package com.brikton.labapps.msusuario.rest;

import com.brikton.labapps.msusuario.domain.Obra;
import com.brikton.labapps.msusuario.domain.TipoObra;
import com.brikton.labapps.msusuario.exceptions.ClienteNoEncontradoException;
import com.brikton.labapps.msusuario.exceptions.ObraNoEncontradaException;
import com.brikton.labapps.msusuario.exceptions.ObrasNoAsociadasException;
import com.brikton.labapps.msusuario.service.ObraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/obra")
@CrossOrigin(origins = {"http://localhost:9005", "http://localhost:8181", "http://service-gateway:8181"}, maxAge = 3000)
public class ObraRest {

    protected final Logger logger = LoggerFactory.getLogger(ObraRest.class);

    private final ObraService obraServicio;

    public ObraRest(ObraService obraServicio) {
        this.obraServicio = obraServicio;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getObraById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(this.obraServicio.getObraById(id));
        } catch (ObraNoEncontradaException e) {
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El tipo de obra que se requiere no es válido. \nLos siguientes valores son válidos: \"REFORMA, CASA, EDIFICIO y VIAL\".");
        }
    }

    @PostMapping
    public ResponseEntity<?> saveObra(@RequestBody Obra nueva) {
        try {
            return ResponseEntity.ok(this.obraServicio.saveObra(nueva));
        } catch (ClienteNoEncontradoException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateObra(@RequestBody Obra nueva, @PathVariable Integer id) {
        try {
            return ResponseEntity.ok(this.obraServicio.updateObra(nueva, id));
        } catch (ObraNoEncontradaException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<String> handleError(HttpServletRequest req, Exception ex) {
        logger.error(ex.getMessage());
        ModelAndView mav = new ModelAndView();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El tipo de obra que se requiere no es válido. Los siguientes valores son válidos: REFORMA, CASA, EDIFICIO y VIAL.");
    }

    @GetMapping(path = "/obrasPorCliente/{clienteId}")
    public ResponseEntity<?> getObrasByClienteId(@PathVariable Integer clienteId) {
        try {
            return ResponseEntity.ok(this.obraServicio.getObrasByClienteId(clienteId));
        } catch (ClienteNoEncontradoException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ObrasNoAsociadasException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(path = "/obrasPorCliente/cuit/{clienteCuit}")
    public ResponseEntity<?> getObrasByClienteCuit(@PathVariable String clienteCuit) {
        try {
            return ResponseEntity.ok(this.obraServicio.getObrasByClienteCuit(clienteCuit));
        } catch (ClienteNoEncontradoException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ObrasNoAsociadasException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(path = "/saldo/{obraId}")
    public ResponseEntity<?> getSaldoPorObrasByClient(@PathVariable Integer obraId) {
        try {
            return ResponseEntity.ok(this.obraServicio.getSaldoPorObrasByClient(obraId));
        } catch (ObraNoEncontradaException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteObra(@PathVariable Integer id) {
        try {
            obraServicio.deleteObra(id);
            return ResponseEntity.ok().body("La obra con el id: " + id + " ha sido borrada");
        } catch (ObraNoEncontradaException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
