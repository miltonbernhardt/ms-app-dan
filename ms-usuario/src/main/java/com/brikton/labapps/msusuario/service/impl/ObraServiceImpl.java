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
    ObraRepository obraRepository;

    @Override
    public void deleteObra(Integer id) throws Exception {
        Optional<Obra> obra = obraRepository.findById(id);
        if (obra.isPresent())
            obraRepository.deleteById(id);
        else
            throw new Exception("No se ha encontrando una obra con el id: " + id);
    }

    public List<Obra> getAllObrasByTipo(TipoObra tipoObraId) {
        if (tipoObraId == null) {
            return this.obraRepository.findAll();
        } else {
            return this.obraRepository.findAllByTipoObra(tipoObraId);
        }
    }

    public Optional<Obra> getObraById(Integer id) throws Exception {
        Optional<Obra> obra = this.obraRepository.findById(id);
        if (obra.isEmpty())
            throw new Exception("No existe una obra que tenga el id: " + id);
        return obra;
    }

    public Obra saveObra(Obra obra) throws Exception {
        Optional<Cliente> clienteBuscado = obra.getCliente().getId() != null
                ? this.clienteServicio.getClienteById(obra.getCliente().getId())
                : this.clienteServicio.getClienteByCuit(obra.getCliente().getCuit());

        if (clienteBuscado.isPresent()) {
            Cliente cliente = clienteBuscado.get();
            obra.setCliente(cliente);
            this.obraRepository.save(obra);
            return obra;
        } else
            throw new Exception("No se encontró el cliente indicado.");
    }

    @Override
    public Obra updateObra(Obra nueva, Integer id) throws Exception {
        Optional<Obra> obra = obraRepository.findById(id);
        if (obra.isPresent()) {
            obra.get().setDireccion(nueva.getDireccion() == null ? obra.get().getDireccion() : nueva.getDireccion());
            obra.get().setDescripcion(nueva.getDescripcion() == null ? obra.get().getDescripcion() : nueva.getDescripcion());
            obra.get().setLatitud(nueva.getLatitud() == null ? obra.get().getLatitud() : nueva.getLatitud());
            obra.get().setSuperficie(nueva.getSuperficie() == null ? obra.get().getSuperficie() : nueva.getSuperficie());
            obra.get().setLongitud(nueva.getLongitud() == null ? obra.get().getLongitud() : nueva.getLongitud());
            obra.get().setTipoObra(nueva.getTipoObra() == null ? obra.get().getTipoObra() : nueva.getTipoObra());
            return obraRepository.save(obra.get());
        } else throw new Exception("No se ha encontrado una obra con el id: " + id);
    }

    public List<Obra> getObrasByClienteId(Integer clienteId) throws Exception {
        Optional<Cliente> cliente = this.clienteServicio.getClienteById(clienteId);

        if (cliente.isEmpty())
            throw new Exception("No se encontró un cliente con el id " + clienteId + ".");

        List<Obra> obras = this.obraRepository.findAllByCliente(cliente.get().getId());

        if (obras.size() <= 0)
            throw new Exception("El cliente con el id " + clienteId + " no posee obras asociadas.");

        return obras;
    }

    @Override
    public List<Obra> getObrasByClienteCuit(String clienteCuit) throws Exception {
        Optional<Cliente> cliente = this.clienteServicio.getClienteByCuit(clienteCuit);

        if (cliente.isEmpty())
            throw new Exception("No se encontró un cliente con el cuit " + clienteCuit + ".");

        List<Obra> obras = this.obraRepository.findAllByCliente(cliente.get().getId());

        if (obras.size() <= 0)
            throw new Exception("El cliente con el cuit " + clienteCuit + " no posee obras asociadas.");

        return obras;
    }

    public Double getSaldoPorObrasByClient(Integer obraId) throws Exception {
        Optional<Obra> obra = this.obraRepository.findById(obraId);
        if (obra.isEmpty()) {
            throw new Exception("No se encontró una obra con el id " + obraId + ".");
        }
        return obra.get().getCliente().getMaxCuentaCorriente();
    }
}
