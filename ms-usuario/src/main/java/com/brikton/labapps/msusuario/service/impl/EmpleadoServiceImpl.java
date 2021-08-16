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
    public Empleado saveEmpleado(Empleado empleado) {
        Usuario usuario = new Usuario();
        usuario.setMail(empleado.getNombre().toLowerCase().replaceAll("\\s+", ""));
        usuario.setPassword("dan2021");
        usuario.setTipoUsuario(TipoUsuario.VENDEDOR);
        this.usuarioRepository.save(usuario);
        empleado.setUser(usuario);
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado updateEmpleado(Empleado nuevo, Integer id) throws Exception {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        if (empleado.isPresent()) {
            empleado.get().setNombre(nuevo.getNombre());
            empleado.get().setUser(nuevo.getUser());
            return empleadoRepository.save(empleado.get());
        } else throw new Exception("No se ha encontrado un empleado con el id: " + id);
    }

    @Override
    public void deleteEmpleado(Integer id) throws Exception {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        if (empleado.isPresent()) {
            empleadoRepository.deleteById(id);
        } else throw new Exception("No se ha encontrado un empleado con el id: " + id);
    }

    @Override
    public Empleado getEmpleado(Integer id) throws Exception {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        if (empleado.isEmpty())
            throw new Exception("No se ha encontrado un empleado con el id: " + id);
        return empleado.get();
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
