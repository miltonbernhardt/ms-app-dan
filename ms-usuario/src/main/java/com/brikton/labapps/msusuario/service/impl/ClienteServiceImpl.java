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
    public Cliente getClienteById(Integer id) {
        Optional<Cliente> cliente = this.clienteRepository.findById(id);
        if (cliente.isEmpty() || cliente.get().getFechaBaja() != null)
            return null;
        return cliente.get();
    }

    @Override
    public Cliente getClienteByCuit(String cuit) {
        List<Cliente> clientes = this.clienteRepository.findByCuit(cuit);
        if (clientes.size() > 0) {
            return clientes.get(0);
        }
        return null;
    }

    @Override
    public Cliente getClienteByRazonSocial(String razonSocial) {
        Optional<Cliente> c = this.clienteRepository.findByRazonSocial(razonSocial);
        if (c.isEmpty() || c.get().getFechaBaja() != null)
            return null;
        return c.get();
    }


    @Override
    @Transactional
    public Cliente saveCliente(Cliente cliente) throws Exception {
        if (cliente.getId() == null || this.getClienteById(cliente.getId()) == null) {
            Cliente clienteAntiguo = this.getClienteByCuit(cliente.getCuit());

            if (clienteAntiguo != null) {
                cliente.setId(clienteAntiguo.getId());
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
        Cliente cliente = this.getClienteById(id);
        if (cliente == null) {
            throw new Exception("No existen clientes con el id: " + id);
        }
        cliente.setFechaBaja(new Date());
        this.clienteRepository.save(cliente);
    }
}

