package com.brikton.labapps.msliquidacion.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class LiquidacionSueldo {

    @Id
    @Column(name = "id_liquidacion")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    private LocalDate fecha;
    private Double monto;

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public LiquidacionSueldo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
