package com.brikton.labapps.msusuario.service.impl;

import com.brikton.labapps.msusuario.domain.Cliente;
import com.brikton.labapps.msusuario.domain.TipoUsuario;
import com.brikton.labapps.msusuario.domain.Usuario;
import com.brikton.labapps.msusuario.repositorios.ClienteRepository;
import com.brikton.labapps.msusuario.repositorios.UsuarioRepository;
import com.brikton.labapps.msusuario.service.ClienteService;
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
    UsuarioRepository usuarioRepository;

    @Override
    public List<Cliente> listarClientes() {
        return this.clienteRepository.findByFechaBajaIsNull();
    }

    @Override
    public Optional<Cliente> buscarClientePorId(Integer id) throws Exception {
        Optional<Cliente> cliente = this.clienteRepository.findById(id);

        if (cliente.isEmpty() || cliente.get().getFechaBaja() != null)
            throw new Exception("No existen clientes con el id: " + id);
        return cliente;
    }

    @Override
    public Optional<Cliente> buscarClientePorCuit(String cuit) throws Exception {
        List<Cliente> clientes = this.clienteRepository.findByCuit(cuit);
        if (clientes.size() > 0) {
            return Optional.ofNullable(clientes.get(0));
        }
        throw new Exception("No existen clientes con el número de CUIT: " + cuit);
    }

    @Override
    public Optional<Cliente> buscarClientePorRazonSocial(String razonSocial) throws Exception {
        Optional<Cliente> c = this.clienteRepository.findByRazonSocial(razonSocial);
        if (c.isEmpty() || c.get().getFechaBaja() != null)
            throw new Exception("No existen clientes con la razón social " + razonSocial);
        return c;
    }

    @Override
    @Transactional
    public Cliente guardarCliente(Cliente cliente) throws Exception {
        if (cliente.getId() == null || this.buscarClientePorId(cliente.getId()).isEmpty()) {
            Optional<Cliente> clienteAntiguo;

            try {
                clienteAntiguo = this.buscarClientePorCuit(cliente.getCuit());
            } catch (Exception e) {
                clienteAntiguo = Optional.empty();
            }

            if (clienteAntiguo.isPresent()) {
                cliente.setId(clienteAntiguo.get().getId());
            } else {
                Usuario usuario = new Usuario();
                usuario.setMail(cliente.getRazonSocial().toLowerCase().replaceAll("\\s+", ""));
                usuario.setPassword("dan2021");
                usuario.setTipoUsuario(TipoUsuario.CLIENTE);
                this.usuarioRepository.save(usuario);
                cliente.setUsuario(usuario);
            }
        }
        return this.clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public void bajaCliente(Integer id) throws Exception {
        // TODO: validar que no haya realizado pedidos
        Optional<Cliente> cliente = this.buscarClientePorId(id);

        if (cliente.isEmpty()) {
            throw new Exception("No existen clientes con el id: " + id);
        }

        cliente.get().setFechaBaja(new Date());
        this.clienteRepository.save(cliente.get());
    }
}

