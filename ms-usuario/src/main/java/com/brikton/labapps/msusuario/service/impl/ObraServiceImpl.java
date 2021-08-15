package com.brikton.labapps.msusuario.service.impl;

import com.brikton.labapps.msusuario.domain.Cliente;
import com.brikton.labapps.msusuario.domain.Obra;
import com.brikton.labapps.msusuario.domain.TipoObra;
import com.brikton.labapps.msusuario.repositorios.ObraRepository;
import com.brikton.labapps.msusuario.service.ClienteService;
import com.brikton.labapps.msusuario.service.ObraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObraServiceImpl implements ObraService {

    @Autowired
    ClienteService clienteServicio;

    @Autowired
    ObraRepository repositorio;

    public List<Obra> listarObras(TipoObra tipoObraId) {
        if (tipoObraId == null) {
            return this.repositorio.findAll();
        } else {
            return this.repositorio.findAllByTipoObra(tipoObraId);
        }
    }

    public Optional<Obra> buscarObraPorId(Integer id) throws Exception {
        Optional<Obra> o = this.repositorio.findById(id);
        if (o.isEmpty())
            throw new Exception("No existe una obra que tenga el id: " + id);
        return o;
    }

    // public Obra guardarObra(Obra o, Integer clienteId) throws Exception {
    // 	Optional<Cliente> clienteBuscado = this.clienteServicio.buscarClientePorId(clienteId);
    // 	if(clienteBuscado.isPresent()) {
    // 		if(o.getId() != null && this.buscarObraPorId(o.getId()).isPresent() && clienteId == null) {
    // 			this.repositorio.save(o);
    // 		} else {
    // 				clienteBuscado.get().agregarObra(o);
    // 				o.setCliente(clienteBuscado.get());
    // 				this.repositorio.save(o);
    // 				this.clienteServicio.guardarCliente(clienteBuscado.get());
    // 		}
    // 		return o;
    // 	} else {
    // 		throw new Exception("No se encontro el cliente con id "+ clienteId);
    // 	}
    // }

    public Obra guardarObra(Obra obra) throws Exception {
        Optional<Cliente> clienteBuscado = obra.getCliente().getId() != null
                ? this.clienteServicio.buscarClientePorId(obra.getCliente().getId())
                : this.clienteServicio.buscarClientePorCuit(obra.getCliente().getCuit());

        if (clienteBuscado.isPresent()) {
            Cliente cliente = clienteBuscado.get();
            obra.setCliente(cliente);
            //cliente.agregarObra(obra);
            //this.clienteServicio.guardarCliente(cliente);
            this.repositorio.save(obra);
            return obra;
        } else throw new Exception("No se encontró el cliente indicado.");
    }

    public List<Obra> listarObrasPorCliente(Integer clienteId) throws Exception {
        Optional<Cliente> cliente = this.clienteServicio.buscarClientePorId(clienteId);

        if (cliente.isEmpty())
            throw new Exception("No se encontró un cliente con el id " + clienteId + ".");

        List<Obra> obras = this.repositorio.findAllByCliente(cliente.get().getId());

        if (obras.size() <= 0)
            throw new Exception("El cliente con el id " + clienteId + " no posee obras asociadas.");

        return obras;
    }

    @Override
    public List<Obra> listarObrasPorCliente(String clienteCuit) throws Exception {
        Optional<Cliente> cliente = this.clienteServicio.buscarClientePorCuit(clienteCuit);

        if (cliente.isEmpty())
            throw new Exception("No se encontró un cliente con el cuit " + clienteCuit + ".");

        List<Obra> obras = this.repositorio.findAllByCliente(cliente.get().getId());

        if (obras.size() <= 0)
            throw new Exception("El cliente con el cuit " + clienteCuit + " no posee obras asociadas.");

        return obras;
    }

    public Double buscarSaldoClienteDeObra(Integer obraId) throws Exception {
        Optional<Obra> o = this.repositorio.findById(obraId);
        if (o.isEmpty()) {
            throw new Exception("No se encontró una obra con el id " + obraId + ".");
        }
        return o.get().getCliente().getMaxCuentaCorriente();
    }
}
