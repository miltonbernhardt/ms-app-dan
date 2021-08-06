package com.brikton.labapps.msusuario.repositorios;

import com.brikton.labapps.msusuario.domain.Empleado;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepositorio extends JpaRepository<Empleado,Integer>{
    
}
