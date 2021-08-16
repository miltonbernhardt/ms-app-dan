package com.brikton.labapps.msusuario.service;

import com.brikton.labapps.msusuario.domain.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {

    Empleado saveEmpleado(Empleado nuevo);

    Empleado updateEmpleado(Empleado nuevo, Integer id) throws Exception;

    void deleteEmpleado(Integer id) throws Exception;

    Empleado getEmpleado(Integer id) throws Exception;

    List<Empleado> getAllEmpleados();

    Optional<Empleado> getEmpleadoByNombre(String nombre) throws Exception;

}
