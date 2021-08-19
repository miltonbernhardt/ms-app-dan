package com.brikton.labapps.msproducto.repository;

import java.util.List;

import com.brikton.labapps.msproducto.domain.Unidad;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadRepository extends JpaRepository<Unidad, Integer> {

    public List<Unidad> findByDescripcion(String descripcion);
}
