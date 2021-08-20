package com.brikton.labapps.msliquidacion.service;

import com.brikton.labapps.msliquidacion.domain.LiquidacionSueldo;
import com.brikton.labapps.msliquidacion.domain.Sueldo;
import java.util.List;

public interface LiquidacionService {
  List<LiquidacionSueldo> liquidarSueldoTodos();

  List<LiquidacionSueldo> getLiquidaciones();

  Double getComisiones(Integer empleado) throws Exception;

  LiquidacionSueldo liquidarSueldoEmpleado(Integer empleado) throws Exception;

  void actualizarSueldoEmpleado(Sueldo sueldo) throws Exception;

  Sueldo getSueldoEmpleado(Integer empleado) throws Exception;
}
