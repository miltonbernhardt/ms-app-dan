package com.brikton.labapps.msusuario.service.impl;

import com.brikton.labapps.msusuario.domain.Cliente;
import com.brikton.labapps.msusuario.domain.TipoUsuario;
import com.brikton.labapps.msusuario.domain.Usuario;
import com.brikton.labapps.msusuario.repositories.ClienteRepository;
import com.brikton.labapps.msusuario.service.ClienteService;
import com.brikton.labapps.msusuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    UsuarioService usuarioService;

    @Override
    public List<Cliente> listarClientes() {
        return this.clienteRepository.findByFechaBajaIsNull();
    }

    @Override
    public Optional<Cliente> getClienteById(Integer id) throws Exception {
        Optional<Cliente> cliente = this.clienteRepository.findById(id);

        if (cliente.isEmpty() || cliente.get().getFechaBaja() != null)
            throw new Exception("No existen clientes con el id: " + id);
        return cliente;
    }

    @Override
    public Optional<Cliente> getClienteByCuit(String cuit) throws Exception {
        List<Cliente> clientes = this.clienteRepository.findByCuit(cuit);
        if (clientes.size() > 0) {
            return Optional.ofNullable(clientes.get(0));
        }
        throw new Exception("No existen clientes con el número de CUIT: " + cuit);
    }

    @Override
    public Optional<Cliente> getClienteByRazonSocial(String razonSocial) throws Exception {
        Optional<Cliente> c = this.clienteRepository.findByRazonSocial(razonSocial);
        if (c.isEmpty() || c.get().getFechaBaja() != null)
            throw new Exception("No existen clientes con la razón social " + razonSocial);
        return c;
    }


    @Override
    @Transactional
    public Cliente saveCliente(Cliente cliente) throws Exception {
        if (cliente.getId() == null || this.getClienteById(cliente.getId()).isEmpty()) {
            Optional<Cliente> clienteAntiguo;

            try {
                clienteAntiguo = this.getClienteByCuit(cliente.getCuit());
            } catch (Exception e) {
                clienteAntiguo = Optional.empty();
            }

            if (clienteAntiguo.isPresent()) {
                cliente.setId(clienteAntiguo.get().getId());
            } else {
                Usuario usuario = new Usuario();
                usuario.setUsername(cliente.getRazonSocial());
                usuario.setTipoUsuario(TipoUsuario.CLIENTE);
                this.usuarioService.saveUsuario(usuario);
                cliente.setUsuario(usuario);
            }
        }
        return this.clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public void bajaCliente(Integer id) throws Exception {
        Optional<Cliente> cliente = this.getClienteById(id);
        if (cliente.isEmpty()) {
            throw new Exception("No existen clientes con el id: " + id);
        }
        cliente.get().setFechaBaja(new Date());
        this.clienteRepository.save(cliente.get());
    }
}

