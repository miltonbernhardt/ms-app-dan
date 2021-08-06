package com.brikton.labapps.msusuario.servicioImplementacion;

import java.util.List;
import java.util.Optional;

import com.brikton.labapps.msusuario.domain.Empleado;
import com.brikton.labapps.msusuario.domain.Usuario;
import com.brikton.labapps.msusuario.repositorios.EmpleadoRepositorio;
import com.brikton.labapps.msusuario.repositorios.UsuarioRepositorio;
import com.brikton.labapps.msusuario.servicioInterfaz.EmpleadoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServicioImplementacion implements EmpleadoServicio {

    @Autowired
    EmpleadoRepositorio repositorio;

    @Override
    public void crearEmpleado(Empleado nuevo) {

        // Usuario u = new Usuario();
        // u.setUser(nuevo.getMail());
        // u.setPassword("1234");

        // usuarioRepositorio.save(u);

        // nuevo.setUser(u);

        repositorio.save(nuevo);    
    }

    @Override
    public void actualizarEmpleado(Empleado nuevo, Integer id) throws Exception {
        Optional<Empleado> e = repositorio.findById(id);
        if (e.isPresent()){
            e.get().setMail(nuevo.getMail());
            e.get().setUser(nuevo.getUser());
            repositorio.save(e.get());
        } else throw new Exception("Empleado id:" + id + " no encontrado");
    }

    @Override
    public void borrarEmpleado(Integer id) throws Exception {
        Optional<Empleado> e = repositorio.findById(id);
        if (e.isPresent()){
            repositorio.deleteById(id);
        } else throw new Exception("Empleado id:" + id + " no encontrado");
    }

    @Override
    public Empleado getEmpleado(Integer id) throws Exception {
        Optional<Empleado> e = repositorio.findById(id);
        if (!e.isPresent()) throw new Exception("Empleado id:" + id + " no encontrado");
        return e.get();
    }

    @Override
    public List<Empleado> listaEmpleados() {
        return repositorio.findAll();
    }

    @Override
    public Empleado empleadoPorNombre(String usuario) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
    
}
