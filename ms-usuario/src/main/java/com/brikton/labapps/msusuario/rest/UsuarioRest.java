package com.brikton.labapps.msusuario.rest;

import com.brikton.labapps.msusuario.domain.Usuario;
import com.brikton.labapps.msusuario.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRest {

    private UsuarioService usuarioService;

    public UsuarioRest(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(path = "/{mail}")
    public ResponseEntity<?> getUsuarioByMail(@PathVariable String mail) {
        try {
            return ResponseEntity.of(this.usuarioService.getUsuario(mail));
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
