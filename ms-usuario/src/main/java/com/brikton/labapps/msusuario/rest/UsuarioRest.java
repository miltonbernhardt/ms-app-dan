package com.brikton.labapps.msusuario.rest;

import com.brikton.labapps.msusuario.domain.Usuario;
import com.brikton.labapps.msusuario.exceptions.UsuarioInvalidoException;
import com.brikton.labapps.msusuario.exceptions.UsuarioNoEncontradoException;
import com.brikton.labapps.msusuario.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = {"http://localhost:9005", "http://ms-frontend:9005", "http://localhost:8181", "http://service-gateway:8181"}, maxAge = 3000)
public class UsuarioRest {

    protected final Logger logger = LoggerFactory.getLogger(UsuarioRest.class);

    private final UsuarioService usuarioService;

    public UsuarioRest(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<?> getUsuarioByUsername(@PathVariable String username) {
        try {
            return ResponseEntity.ok(this.usuarioService.getUsuario(username));
        } catch (UsuarioNoEncontradoException e) {
            logger.error(e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.ok(this.usuarioService.saveUsuario(usuario));
        } catch (UsuarioInvalidoException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
