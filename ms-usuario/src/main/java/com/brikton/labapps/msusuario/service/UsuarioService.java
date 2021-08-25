package com.brikton.labapps.msusuario.service;

import com.brikton.labapps.msusuario.domain.Usuario;

import java.util.Optional;

public interface UsuarioService {

    Usuario saveUsuario(Usuario usuario) throws Exception;

    Optional<Usuario> getUsuario(String mail) throws Exception;
}
