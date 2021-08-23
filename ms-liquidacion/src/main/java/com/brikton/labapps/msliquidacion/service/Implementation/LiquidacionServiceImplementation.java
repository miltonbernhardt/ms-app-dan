package com.brikton.labapps.msliquidacion.service.Implementation;

import com.brikton.labapps.msliquidacion.domain.LiquidacionSueldo;
import com.brikton.labapps.msliquidacion.domain.Sueldo;
import com.brikton.labapps.msliquidacion.repository.EmpleadoRepository;
import com.brikton.labapps.msliquidacion.repository.LiquidacionSueldoRepository;
import com.brikton.labapps.msliquidacion.repository.SueldoRepository;
import com.brikton.labapps.msliquidacion.service.LiquidacionService;
import com.brikton.labapps.msliquidacion.service.VentaService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LiquidacionServiceImplementation implements LiquidacionService {

    private final LiquidacionSueldoRepository liquidacionRepository;
    private final SueldoRepository sueldoRepository;
    private final EmpleadoRepository empleadoRepository;
    private final VentaService ventaService;

    public LiquidacionServiceImplementation(
            LiquidacionSueldoRepository liquidacionRepository,
            SueldoRepository sueldoRepository,
            EmpleadoRepository empleadoRepository,
            VentaService ventaService
    ) {
        this.liquidacionRepository = liquidacionRepository;
        this.sueldoRepository = sueldoRepository;
        this.empleadoRepository = empleadoRepository;
        this.ventaService = ventaService;
    }

    @Override
    public List<LiquidacionSueldo> liquidarSueldoTodos() {
        List<LiquidacionSueldo> liquidacion = new ArrayList<>();
        List<Sueldo> sueldos = sueldoRepository.findAll();
        for (Sueldo s : sueldos) {
            LiquidacionSueldo ls = new LiquidacionSueldo();
            ls.setFecha(Instant.now());
            ls.setEmpleado(s.getEmpleado());
            Double monto = s.getMonto();
            Double comision = ventaService.getMontoVentasMes(s.getEmpleado()) * s.getComision();
            ventaService.saldarVentas(s.getEmpleado());
            ls.setMonto(monto + comision);
            liquidacion.add(ls);
        }
        liquidacionRepository.saveAll(liquidacion);
        return liquidacion;
    }

    @Override
    public List<LiquidacionSueldo> getLiquidaciones() {
        return liquidacionRepository.findAll();
    }

    @Override
    public void actualizarSueldoEmpleado(Sueldo sueldo) {
        Optional<Sueldo> sueldoViejo = sueldoRepository.findAll().stream()
                .filter(s -> s.getEmpleado().getId().equals(sueldo.getEmpleado().getId())).findFirst();
        if (sueldoViejo.isPresent()) {
            sueldoViejo.get().setComision(sueldo.getComision());
            sueldoViejo.get().setMonto(sueldo.getMonto());
            sueldoRepository.save(sueldoViejo.get());
        } else {
            empleadoRepository.save(sueldo.getEmpleado());
            sueldoRepository.save(sueldo);
        }
    }

    @Override
    public Sueldo getSueldoEmpleado(Integer idEmpleado) throws Exception {
        Optional<Sueldo> sueldo = sueldoRepository.findAll().stream()
                .filter(s -> s.getEmpleado().getId().equals(idEmpleado)).findFirst();
        if (sueldo.isEmpty())
            throw new Exception("No se encontró el empleado id: " + idEmpleado);
        return sueldo.get();
    }

    @Override
    public Double getComisiones(Integer idEmpleado) throws Exception {
        Optional<Sueldo> sueldo = sueldoRepository.findAll().stream()
                .filter(s -> s.getEmpleado().getId().equals(idEmpleado)).findFirst();
        if (sueldo.isEmpty())
            throw new Exception("No se encontró el empleado id: " + idEmpleado);
        Double porcentajeVenta = sueldo.get().getComision();
        return ventaService.getMontoVentasMes(sueldo.get().getEmpleado()) * porcentajeVenta;
    }

    @Override
    public LiquidacionSueldo liquidarSueldoEmpleado(Integer idEmpleado) throws Exception {
        Optional<Sueldo> sueldo = sueldoRepository.findAll().stream()
                .filter(s -> s.getEmpleado().getId().equals(idEmpleado)).findFirst();
        if (sueldo.isEmpty())
            throw new Exception("No se encontró el empleado id: " + idEmpleado);
        Double porcentajeVenta = sueldo.get().getComision();
        Double comision = ventaService.getMontoVentasMes(sueldo.get().getEmpleado()) * porcentajeVenta;
        LiquidacionSueldo ls = new LiquidacionSueldo();
        ls.setEmpleado(sueldo.get().getEmpleado());
        ls.setFecha(Instant.now());
        ls.setMonto(sueldo.get().getMonto() + comision);
        ventaService.saldarVentas(sueldo.get().getEmpleado());
        liquidacionRepository.save(ls);
        return ls;
    }
}
