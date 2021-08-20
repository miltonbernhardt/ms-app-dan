package com.brikton.labapps.msusuario.rest;

import com.brikton.labapps.msusuario.domain.Usuario;
import com.brikton.labapps.msusuario.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRest {

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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createCliente(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.ok(this.usuarioService.saveUsuario(usuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
