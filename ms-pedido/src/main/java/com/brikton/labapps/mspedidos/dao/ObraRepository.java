package com.brikton.labapps.mspedidos.dao;

import com.brikton.labapps.mspedidos.domain.Obra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraRepository extends JpaRepository<Obra,Integer>{
    
}
