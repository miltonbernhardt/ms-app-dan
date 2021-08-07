package com.brikton.labapps.msliquidacion.service.Implementation;

import com.brikton.labapps.msliquidacion.domain.Empleado;
import com.brikton.labapps.msliquidacion.domain.Venta;
import com.brikton.labapps.msliquidacion.repository.VentaRepository;
import com.brikton.labapps.msliquidacion.service.VentaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaServiceImplementation implements VentaService {

  @Autowired
  VentaRepository repository;

  @Override
  public Double getMontoVentasMes(Empleado empleado) {
    List<Venta> ventasEmpleado = repository.ventasPendientesEmpleado(empleado);
    Double montoVentas = 0d;
    for (Venta v : ventasEmpleado)
      montoVentas += v.getMonto();
    return montoVentas;
  }

  @Override
  public void saldarVentas(Empleado empleado) {
    List<Venta> ventasEmpleado = repository.ventasPendientesEmpleado(empleado);
    ventasEmpleado.forEach(v -> v.setPendienteDeCobro(false));
    repository.saveAll(ventasEmpleado);
  }

  @Override
  public List<Venta> registrarVentas(List<Venta> ventas) {
    return repository.saveAll(ventas);
  }
}
