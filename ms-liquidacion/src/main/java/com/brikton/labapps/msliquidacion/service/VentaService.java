package com.brikton.labapps.msliquidacion.service;

import java.util.List;

import com.brikton.labapps.msliquidacion.domain.Empleado;
import com.brikton.labapps.msliquidacion.domain.Venta;

public interface VentaService {
  public Double getMontoVentasMes(Empleado empleado);

  public void saldarVentas(Empleado empleado);

  public List<Venta> registrarVentas(List<Venta> ventas);
}
