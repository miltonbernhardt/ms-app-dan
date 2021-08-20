package com.brikton.labapps.msusuario.repositories;

import java.util.List;

import com.brikton.labapps.msusuario.domain.Obra;
import com.brikton.labapps.msusuario.domain.TipoObra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ObraRepository extends JpaRepository<Obra, Integer>{

	@Query("SELECT o FROM Obra o WHERE o.cliente.id = :idCliente")
	List<Obra> findAllByCliente(@Param("idCliente") Integer idCliente);

	@Query("SELECT o FROM Obra o WHERE o.tipoObra = :tipoObra")
	List<Obra> findAllByTipoObra(@Param("tipoObra") TipoObra tipoObra); 
}
