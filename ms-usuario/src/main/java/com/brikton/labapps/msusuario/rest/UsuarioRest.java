package com.brikton.labapps.msusuario.rest;

import com.brikton.labapps.msusuario.domain.Usuario;
import com.brikton.labapps.msusuario.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = {"http://localhost:9005", "http://ms-frontend:9005", "http://localhost:8181", "http://service-gateway:8181"}, maxAge = 3000)
public class UsuarioRest {

    protected final Logger logger = LoggerFactory.getLogger(UsuarioRest.class);

    private UsuarioService usuarioService;

    public UsuarioRest(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<?> getUsuarioByUsername(@PathVariable String username) {
        try {
            Optional<Usuario> usuario = this.usuarioService.getUsuario(username);
            if (usuario.isPresent()) {
                return ResponseEntity.ok(usuario);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró al usuario " + username);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo obtener el usuario debido a un error interno.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.ok(this.usuarioService.saveUsuario(usuario));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo crear el usuario debido a un error interno.");
        }
    }
}
