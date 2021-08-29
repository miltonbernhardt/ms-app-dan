package com.brikton.labapps.msusuario.service.impl;

import com.brikton.labapps.msusuario.domain.Cliente;
import com.brikton.labapps.msusuario.domain.TipoUsuario;
import com.brikton.labapps.msusuario.domain.Usuario;
import com.brikton.labapps.msusuario.exceptions.ClienteNoEncontradoException;
import com.brikton.labapps.msusuario.exceptions.UsuarioInvalidoException;
import com.brikton.labapps.msusuario.repositories.ClienteRepository;
import com.brikton.labapps.msusuario.service.ClienteService;
import com.brikton.labapps.msusuario.service.UsuarioService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final UsuarioService usuarioService;

    public ClienteServiceImpl(ClienteRepository clienteRepository, UsuarioService usuarioService) {
        this.clienteRepository = clienteRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public List<Cliente> listarClientes() {
        return this.clienteRepository.findByFechaBajaIsNull();
    }

    @Override
    public Cliente getClienteById(Integer id) throws ClienteNoEncontradoException {
        Optional<Cliente> cliente = this.clienteRepository.findById(id);
        if (cliente.isEmpty() || cliente.get().getFechaBaja() != null)
            throw new ClienteNoEncontradoException("No existen clientes activos con el id: " + id);
        return cliente.get();
    }

    @Override
    public Cliente getClienteByCuit(String cuit) throws ClienteNoEncontradoException {
        List<Cliente> clientes = this.clienteRepository.findByCuit(cuit);
        if (clientes.size() > 0 && clientes.get(0).getFechaBaja() != null)
            return clientes.get(0);
        throw new ClienteNoEncontradoException("No existen clientes activos con el cuit: " + cuit);
    }

    @Override
    public Cliente getClienteByRazonSocial(String razonSocial) throws ClienteNoEncontradoException {
        Optional<Cliente> c = this.clienteRepository.findByRazonSocial(razonSocial);
        if (c.isEmpty() || c.get().getFechaBaja() != null)
            throw new ClienteNoEncontradoException("No existen clientes activos con la razón social: " + razonSocial);
        return c.get();
    }

    @Override
    @Transactional
    public Cliente saveCliente(Cliente cliente) throws ClienteNoEncontradoException, UsuarioInvalidoException {
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
    public Cliente updateCliente(Cliente cliente) throws ClienteNoEncontradoException, UsuarioInvalidoException {
        if ((cliente.getId() == null || cliente.getId() < 0) && (cliente.getCuit() == null || cliente.getCuit().isEmpty())) {
            throw new ClienteNoEncontradoException("No existe dicho cliente para actualizarlo");
        }
        return saveCliente(cliente);
    }

    @Override
    @Transactional
    public void bajaCliente(Integer id) throws ClienteNoEncontradoException {
        Cliente cliente = this.getClienteById(id);
        if (cliente == null) {
            throw new ClienteNoEncontradoException("No existen clientes con el id: " + id);
        }
        cliente.setFechaBaja(new Date());
        this.clienteRepository.save(cliente);
    }
}

