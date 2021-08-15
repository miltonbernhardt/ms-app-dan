package com.brikton.labapps.msusuario.service;

import java.util.List;
import java.util.Optional;

import com.brikton.labapps.msusuario.domain.Empleado;

public interface EmpleadoService {

    void createEmpleado(Empleado nuevo);

    void updateEmpleado(Empleado nuevo, Integer id) throws Exception;

    void deleteEmpleado(Integer id) throws Exception;

    Empleado getEmpleado(Integer id) throws Exception;

    List<Empleado> getAllEmpleados();

    Optional<Empleado> getEmpleadoByNombre(String nombre) throws Exception;
    
}
