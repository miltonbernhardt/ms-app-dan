package com.brikton.labapps.msusuario.repositorios;

import com.brikton.labapps.msusuario.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByFechaBajaIsNull();

    @Query("select c from Cliente c where c.cuit = :cuit and c.fechaBaja is null")
    List<Cliente> findByCuit(@Param("cuit") String cuit);

    Optional<Cliente> findByRazonSocial(String razonSocial);
}
