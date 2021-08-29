package com.brikton.labapps.msusuario.service.impl;

import com.brikton.labapps.msusuario.domain.Usuario;
import com.brikton.labapps.msusuario.exceptions.UsuarioInvalidoException;
import com.brikton.labapps.msusuario.exceptions.UsuarioNoEncontradoException;
import com.brikton.labapps.msusuario.repositories.UsuarioRepository;
import com.brikton.labapps.msusuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Value("${jwtSecret}")
    private String password;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario getUsuario(String username) throws UsuarioNoEncontradoException {
        Optional<Usuario> usuario = this.usuarioRepository.findByUsername(username);
        if (usuario.isEmpty())
            throw new UsuarioNoEncontradoException("No se encontró un usuario con el username: " + username);
        return usuario.get();
    }

    @Override
    @Transactional
    public Usuario saveUsuario(Usuario usuario) throws UsuarioInvalidoException {
        if (usuario.getUsername() == null || usuario.getUsername().isEmpty()) {
            throw new UsuarioInvalidoException("El usuario debe tener un username.");
        } else {
            try {
                this.getUsuario(usuario.getUsername());
                throw new UsuarioInvalidoException("Ya existe un usuario con el username: " + usuario.getUsername());
            } catch (UsuarioNoEncontradoException ignored) {
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

