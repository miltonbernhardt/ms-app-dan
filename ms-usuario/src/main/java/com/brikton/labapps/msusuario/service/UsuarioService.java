package com.brikton.labapps.msusuario.service;

import com.brikton.labapps.msusuario.domain.Usuario;
import com.brikton.labapps.msusuario.exceptions.UsuarioInvalidoException;
import com.brikton.labapps.msusuario.exceptions.UsuarioNoEncontradoException;

public interface UsuarioService {

    Usuario saveUsuario(Usuario usuario) throws UsuarioInvalidoException;

    Usuario getUsuario(String mail) throws UsuarioNoEncontradoException;
}
