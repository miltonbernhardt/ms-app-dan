package com.brikton.labapps.mspedidos.dao;

import com.brikton.labapps.mspedidos.domain.Unidad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadRepository extends JpaRepository<Unidad,Integer> {
    
}
