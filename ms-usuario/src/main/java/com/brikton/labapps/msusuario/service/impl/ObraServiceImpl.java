package com.brikton.labapps.msusuario.service.impl;

import com.brikton.labapps.msusuario.domain.Cliente;
import com.brikton.labapps.msusuario.domain.Obra;
import com.brikton.labapps.msusuario.domain.TipoObra;
import com.brikton.labapps.msusuario.exceptions.ClienteNoEncontradoException;
import com.brikton.labapps.msusuario.exceptions.ObraNoEncontradaException;
import com.brikton.labapps.msusuario.exceptions.ObrasNoAsociadasException;
import com.brikton.labapps.msusuario.repositories.ObraRepository;
import com.brikton.labapps.msusuario.service.ClienteService;
import com.brikton.labapps.msusuario.service.ObraService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObraServiceImpl implements ObraService {

    private final ClienteService clienteServicio;
    private final ObraRepository obraRepository;

    public ObraServiceImpl(ClienteService clienteServicio, ObraRepository obraRepository) {
        this.clienteServicio = clienteServicio;
        this.obraRepository = obraRepository;
    }

    @Override
    public void deleteObra(Integer id) throws ObraNoEncontradaException {
        Optional<Obra> obra = obraRepository.findById(id);
        if (obra.isPresent())
            obraRepository.deleteById(id);
        throw new ObraNoEncontradaException("No se ha encontrando una obra con el id: " + id);
    }

    public List<Obra> getAllObrasByTipo(TipoObra tipoObraId) {
        if (tipoObraId == null) {
            return this.obraRepository.findAll();
        } else {
            return this.obraRepository.findAllByTipoObra(tipoObraId);
        }
    }

    public Obra getObraById(Integer id) throws ObraNoEncontradaException {
        Optional<Obra> obra = this.obraRepository.findById(id);
        if (obra.isEmpty())
            throw new ObraNoEncontradaException("No existe una obra que tenga el id: " + id);
        return obra.get();
    }

    public Obra saveObra(Obra obra) throws ClienteNoEncontradoException {
        Cliente clienteBuscado;
        try {
            clienteBuscado = obra.getCliente().getId() != null
                    ? this.clienteServicio.getClienteById(obra.getCliente().getId())
                    : this.clienteServicio.getClienteByCuit(obra.getCliente().getCuit());
        } catch (ClienteNoEncontradoException e) {
            throw new ClienteNoEncontradoException("No se pudo encontrar un cliente para asociar a la obra " + obra.getDescripcion());
        }

        if (clienteBuscado != null) {
            obra.setCliente(clienteBuscado);
            this.obraRepository.save(obra);
            return obra;
        }
        throw new ClienteNoEncontradoException("No se pudo encontrar un cliente para asociar a la obra " + obra.getDescripcion());
    }

    @Override
    public Obra updateObra(Obra nueva, Integer id) throws ObraNoEncontradaException {
        Optional<Obra> obra = obraRepository.findById(id);
        if (obra.isPresent()) {
            obra.get().setDireccion(nueva.getDireccion() == null ? obra.get().getDireccion() : nueva.getDireccion());
            obra.get().setDescripcion(nueva.getDescripcion() == null ? obra.get().getDescripcion() : nueva.getDescripcion());
            obra.get().setLatitud(nueva.getLatitud() == null ? obra.get().getLatitud() : nueva.getLatitud());
            obra.get().setSuperficie(nueva.getSuperficie() == null ? obra.get().getSuperficie() : nueva.getSuperficie());
            obra.get().setLongitud(nueva.getLongitud() == null ? obra.get().getLongitud() : nueva.getLongitud());
            obra.get().setTipoObra(nueva.getTipoObra() == null ? obra.get().getTipoObra() : nueva.getTipoObra());
            return obraRepository.save(obra.get());
        }
        throw new ObraNoEncontradaException("No se ha encontrado una obra con el id: " + id);
    }

    public List<Obra> getObrasByClienteId(Integer clienteId) throws ClienteNoEncontradoException, ObrasNoAsociadasException {
        Cliente cliente = this.clienteServicio.getClienteById(clienteId);
        List<Obra> obras = this.obraRepository.findAllByCliente(cliente.getId());

        if (obras.size() <= 0)
            throw new ObrasNoAsociadasException("El cliente con el id: " + clienteId + " no posee obras asociadas.");
        return obras;
    }

    @Override
    public List<Obra> getObrasByClienteCuit(String clienteCuit) throws ClienteNoEncontradoException, ObrasNoAsociadasException {
        Cliente cliente = this.clienteServicio.getClienteByCuit(clienteCuit);
        List<Obra> obras = this.obraRepository.findAllByCliente(cliente.getId());

        if (obras.size() <= 0)
            throw new ObrasNoAsociadasException("El cliente con el cuit " + clienteCuit + " no posee obras asociadas.");

        return obras;
    }

    public Double getSaldoPorObrasByClient(Integer obraId) throws ObraNoEncontradaException {
        Optional<Obra> obra = this.obraRepository.findById(obraId);
        if (obra.isEmpty()) {
            throw new ObraNoEncontradaException("No se encontró una obra con el id " + obraId + ".");
        }
        return obra.get().getCliente().getMaxCuentaCorriente();
    }
}
