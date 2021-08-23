package com.brikton.labapps.msusuario.service;

import com.brikton.labapps.msusuario.domain.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> listarClientes();

    Cliente getClienteById(Integer id);

    Cliente getClienteByCuit(String cuit);

    Cliente getClienteByRazonSocial(String razonSocial);

    Cliente saveCliente(Cliente cliente) throws Exception;

    void bajaCliente(Integer id) throws Exception;
}
