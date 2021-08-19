package com.brikton.labapps.msusuario.service.impl;

import com.brikton.labapps.msusuario.domain.Usuario;
import com.brikton.labapps.msusuario.repositorios.UsuarioRepository;
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
    public Optional<Usuario> getUsuario(String mail) throws Exception {
        return this.usuarioRepository.findByMail(mail);
    }

    @Override
    @Transactional
    public Usuario saveUsuario(Usuario usuario) throws Exception {
        if (usuario.getMail() == null || usuario.getMail().isEmpty()) {
            throw new Exception("El usuario debe tener un username/mail.");
        } else {
            if (this.getUsuario(usuario.getMail()).isPresent()) {
                throw new Exception("Ya existe un usuario con ese username/mail");
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

