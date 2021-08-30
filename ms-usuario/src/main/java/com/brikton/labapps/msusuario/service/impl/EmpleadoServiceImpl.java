package com.brikton.labapps.msusuario.service.impl;

import com.brikton.labapps.msusuario.domain.Empleado;
import com.brikton.labapps.msusuario.domain.TipoUsuario;
import com.brikton.labapps.msusuario.domain.Usuario;
import com.brikton.labapps.msusuario.exceptions.EmpleadoNoEncontradoException;
import com.brikton.labapps.msusuario.exceptions.UsuarioInvalidoException;
import com.brikton.labapps.msusuario.repositories.EmpleadoRepository;
import com.brikton.labapps.msusuario.service.EmpleadoService;
import com.brikton.labapps.msusuario.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final UsuarioService usuarioService;

    public EmpleadoServiceImpl(
            EmpleadoRepository empleadoRepository,
            UsuarioService usuarioService
    ) {
        this.empleadoRepository = empleadoRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public Empleado saveEmpleado(Empleado empleado) throws UsuarioInvalidoException {
        Usuario usuario = new Usuario();
        usuario.setUsername(empleado.getNombre());
        usuario.setTipoUsuario(TipoUsuario.VENDEDOR);
        this.usuarioService.saveUsuario(usuario);
        empleado.setUser(usuario);
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado updateEmpleado(Empleado nuevo, Integer id) throws EmpleadoNoEncontradoException {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        if (empleado.isPresent()) {
            empleado.get().setNombre(nuevo.getNombre());
            empleado.get().setUser(nuevo.getUser());
            return empleadoRepository.save(empleado.get());
        }
        throw new EmpleadoNoEncontradoException("No se ha encontrado un empleado con el id: " + id);
    }

    @Override
    public void deleteEmpleado(Integer id) throws EmpleadoNoEncontradoException {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        if (empleado.isPresent())
            empleadoRepository.deleteById(id);
        throw new EmpleadoNoEncontradoException("No se ha encontrado un empleado con el id: " + id);
    }

    @Override
    public Empleado getEmpleado(Integer id) throws EmpleadoNoEncontradoException {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        if (empleado.isEmpty())
            throw new EmpleadoNoEncontradoException("No se ha encontrado un empleado con el id: " + id);
        return empleado.get();
    }

    @Override
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado getEmpleadoByNombre(String nombre) throws EmpleadoNoEncontradoException {
        List<Empleado> empleados = this.empleadoRepository.findByNombre(nombre);
        if (empleados.size() > 0)
            return empleados.get(0);
        throw new EmpleadoNoEncontradoException("No se encontraron empleados con el nombre " + nombre);
    }
}
