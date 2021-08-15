package com.brikton.labapps.msusuario.repositorios;

import com.brikton.labapps.msusuario.domain.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
    
}
