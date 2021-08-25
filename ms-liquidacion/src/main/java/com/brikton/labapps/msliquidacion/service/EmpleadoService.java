package com.brikton.labapps.msliquidacion.service;

import java.util.List;

import com.brikton.labapps.msliquidacion.domain.Empleado;

//TODO no se usa, implementarlo sino no hay relacion entre los empleados de acá y sus liquidaciones
public interface EmpleadoService {
    List<Empleado> getEmpleados() throws Exception;
}
