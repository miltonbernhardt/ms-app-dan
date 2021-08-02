package com.brikton.labapps.mscuentacorriente.repository;

import com.brikton.labapps.mscuentacorriente.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query("select c from Cliente c where c.cuit = :cuit")
    Cliente getClienteByCuit(@Param("cuit") String cuit);
}
