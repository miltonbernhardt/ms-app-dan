package com.brikton.labapps.msusuario.service;

import com.brikton.labapps.msusuario.domain.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> listarClientes();

    Optional<Cliente> getClienteById(Integer id) throws Exception;

    Optional<Cliente> getClienteByCuit(String cuit) throws Exception;

    Optional<Cliente> getClienteByRazonSocial(String razonSocial) throws Exception;

    Cliente saveCliente(Cliente cliente) throws Exception;

    void bajaCliente(Integer id) throws Exception;
}
