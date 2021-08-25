package com.brikton.labapps.msliquidacion.repository;

import com.brikton.labapps.msliquidacion.domain.Empleado;
import com.brikton.labapps.msliquidacion.domain.Venta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
  @Query("select v from Venta v where v.empleado = :id_empleado and v.pendienteDeCobro = true")
  List<Venta> ventasPendientesEmpleado(@Param("id_empleado") Empleado id_empleado);
}
