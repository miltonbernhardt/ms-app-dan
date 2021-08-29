package com.brikton.labapps.msusuario.service;

import com.brikton.labapps.msusuario.domain.Empleado;
import com.brikton.labapps.msusuario.exceptions.EmpleadoNoEncontradoException;
import com.brikton.labapps.msusuario.exceptions.UsuarioInvalidoException;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    Empleado saveEmpleado(Empleado nuevo) throws UsuarioInvalidoException;

    Empleado updateEmpleado(Empleado nuevo, Integer id) throws EmpleadoNoEncontradoException;

    void deleteEmpleado(Integer id) throws EmpleadoNoEncontradoException;

    Empleado getEmpleado(Integer id) throws EmpleadoNoEncontradoException;

    List<Empleado> getAllEmpleados();

    Empleado getEmpleadoByNombre(String nombre) throws EmpleadoNoEncontradoException;
}
