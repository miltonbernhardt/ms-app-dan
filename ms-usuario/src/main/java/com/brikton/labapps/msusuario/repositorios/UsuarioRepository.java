package com.brikton.labapps.msusuario.repositorios;

import com.brikton.labapps.msusuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByMail(String mail);
}
