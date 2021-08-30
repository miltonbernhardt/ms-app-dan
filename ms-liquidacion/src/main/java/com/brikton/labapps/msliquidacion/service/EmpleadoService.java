package com.brikton.labapps.msliquidacion.service;

import java.util.List;

import com.brikton.labapps.msliquidacion.domain.Empleado;

public interface EmpleadoService {
    List<Empleado> getEmpleados() throws Exception;
}
