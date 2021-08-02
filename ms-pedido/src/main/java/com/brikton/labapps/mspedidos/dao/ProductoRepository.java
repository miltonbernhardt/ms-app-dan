package com.brikton.labapps.mspedidos.dao;

import com.brikton.labapps.mspedidos.domain.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer>{
    
}
