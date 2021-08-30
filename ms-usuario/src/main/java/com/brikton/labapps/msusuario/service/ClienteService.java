package com.brikton.labapps.msusuario.service;

import com.brikton.labapps.msusuario.domain.Cliente;
import com.brikton.labapps.msusuario.exceptions.ClienteNoEncontradoException;
import com.brikton.labapps.msusuario.exceptions.UsuarioInvalidoException;

import java.util.List;

public interface ClienteService {
    List<Cliente> listarClientes();

    Cliente getClienteById(Integer id) throws ClienteNoEncontradoException;

    Cliente getClienteByCuit(String cuit) throws ClienteNoEncontradoException;

    Cliente getClienteByRazonSocial(String razonSocial) throws ClienteNoEncontradoException;

    Cliente saveCliente(Cliente cliente) throws ClienteNoEncontradoException, UsuarioInvalidoException;

    Cliente updateCliente(Cliente cliente) throws ClienteNoEncontradoException, UsuarioInvalidoException;

    void bajaCliente(Integer id) throws ClienteNoEncontradoException;
}
