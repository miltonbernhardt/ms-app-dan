package com.brikton.labapps.msliquidacion.service;

import com.brikton.labapps.msliquidacion.domain.LiquidacionSueldo;
import com.brikton.labapps.msliquidacion.domain.Sueldo;
import java.util.List;

public interface LiquidacionService {
  public List<LiquidacionSueldo> liquidarSueldoTodos();

  public List<LiquidacionSueldo> getLiquidaciones();

  public Double getComisiones(Integer empleado) throws Exception;

  public LiquidacionSueldo liquidarSueldoEmpleado(Integer empleado) throws Exception;

  public void actualizarSueldoEmpleado(Sueldo sueldo) throws Exception;

  public Sueldo getSueldoEmpleado(Integer empleado) throws Exception;
}
