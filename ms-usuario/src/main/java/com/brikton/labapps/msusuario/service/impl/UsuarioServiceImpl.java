package com.brikton.labapps.msusuario.service.impl;

import com.brikton.labapps.msusuario.domain.Usuario;
import com.brikton.labapps.msusuario.repositories.UsuarioRepository;
import com.brikton.labapps.msusuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Value("${jwtSecret}")
    private String password;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Usuario> getUsuario(String username) {
        return this.usuarioRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public Usuario saveUsuario(Usuario usuario) throws Exception {
        if (usuario.getUsername() == null || usuario.getUsername().isEmpty()) {
            throw new Exception("El usuario debe tener un username.");
        } else {
            if (this.getUsuario(usuario.getUsername()).isPresent()) {
                throw new Exception("Ya existe un usuario con ese username.");
            }
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass = encoder.encode(password);

        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            pass = encoder.encode(usuario.getPassword());
        }

        usuario.setPassword(pass);

        return this.usuarioRepository.save(usuario);
    }
}

