package com.brikton.labapps.msusuario.repositorios;

import com.brikton.labapps.msusuario.domain.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    @Query("select e from Empleado e where e.nombre = :nombre")
    List<Empleado> findByNombre(@Param("nombre") String nombre);
}
