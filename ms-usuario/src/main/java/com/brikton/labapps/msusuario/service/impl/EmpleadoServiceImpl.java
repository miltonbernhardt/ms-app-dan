package com.brikton.labapps.msusuario.service.impl;

import com.brikton.labapps.msusuario.domain.Empleado;
import com.brikton.labapps.msusuario.domain.TipoUsuario;
import com.brikton.labapps.msusuario.domain.Usuario;
import com.brikton.labapps.msusuario.repositorios.EmpleadoRepository;
import com.brikton.labapps.msusuario.service.EmpleadoService;
import com.brikton.labapps.msusuario.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private EmpleadoRepository empleadoRepository;
    private UsuarioService usuarioService;

    public EmpleadoServiceImpl(
            EmpleadoRepository empleadoRepository,
            UsuarioService usuarioService
    ) {
        this.empleadoRepository = empleadoRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public Empleado saveEmpleado(Empleado empleado) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setMail(empleado.getNombre());
        usuario.setTipoUsuario(TipoUsuario.VENDEDOR);
        this.usuarioService.saveUsuario(usuario);
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
