package com.brikton.labapps.msusuario.service.impl;

import com.brikton.labapps.msusuario.domain.Empleado;
import com.brikton.labapps.msusuario.domain.TipoUsuario;
import com.brikton.labapps.msusuario.domain.Usuario;
import com.brikton.labapps.msusuario.repositorios.EmpleadoRepository;
import com.brikton.labapps.msusuario.repositorios.UsuarioRepository;
import com.brikton.labapps.msusuario.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void createEmpleado(Empleado empleado) {
        Usuario usuario = new Usuario();
        usuario.setMail(empleado.getNombre().toLowerCase().replaceAll("\\s+", ""));
        usuario.setPassword("dan2021");
        usuario.setTipoUsuario(TipoUsuario.VENDEDOR);
        this.usuarioRepository.save(usuario);
        empleado.setUser(usuario);
        empleadoRepository.save(empleado);
    }

    @Override
    public void updateEmpleado(Empleado nuevo, Integer id) throws Exception {
        Optional<Empleado> e = empleadoRepository.findById(id);
        if (e.isPresent()) {
            e.get().setNombre(nuevo.getNombre());
            e.get().setUser(nuevo.getUser());
            empleadoRepository.save(e.get());
        } else throw new Exception("Empleado id:" + id + " no encontrado");
    }

    @Override
    public void deleteEmpleado(Integer id) throws Exception {
        Optional<Empleado> e = empleadoRepository.findById(id);
        if (e.isPresent()) {
            empleadoRepository.deleteById(id);
        } else throw new Exception("Empleado id:" + id + " no encontrado");
    }

    @Override
    public Empleado getEmpleado(Integer id) throws Exception {
        Optional<Empleado> e = empleadoRepository.findById(id);
        if (!e.isPresent())
            throw new Exception("Empleado id:" + id + " no encontrado");
        return e.get();
    }

    @Override
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> getEmpleadoByNombre(String nombre) throws Exception {
        List<Empleado> empleados = this.empleadoRepository.findByNombre(nombre);
        if (empleados.size() > 0) {
            return Optional.ofNullable(empleados.get(0));
        }
        throw new Exception("No existen empleados con el nombre " + nombre);
    }
}
