package com.brikton.labapps.msusuario.servicioInterfaz;

import java.util.List;

import com.brikton.labapps.msusuario.domain.Empleado;

public interface EmpleadoServicio {

    void crearEmpleado(Empleado nuevo);

    void actualizarEmpleado(Empleado nuevo, Integer id) throws Exception;

    void borrarEmpleado(Integer id) throws Exception;

    Empleado getEmpleado(Integer id) throws Exception;

    List<Empleado> listaEmpleados();

    Empleado empleadoPorNombre(String usuario) throws Exception;
    
}
