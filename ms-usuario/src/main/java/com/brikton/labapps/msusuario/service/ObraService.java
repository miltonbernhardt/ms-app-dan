package com.brikton.labapps.msusuario.service;

import com.brikton.labapps.msusuario.domain.Obra;
import com.brikton.labapps.msusuario.domain.TipoObra;
import com.brikton.labapps.msusuario.exceptions.ClienteNoEncontradoException;
import com.brikton.labapps.msusuario.exceptions.ObraNoEncontradaException;
import com.brikton.labapps.msusuario.exceptions.ObrasNoAsociadasException;

import java.util.List;
import java.util.Optional;

public interface ObraService {

    Obra saveObra(Obra obra) throws ClienteNoEncontradoException;

    Obra updateObra(Obra nueva, Integer id) throws ObraNoEncontradaException;

    void deleteObra(Integer id) throws ObraNoEncontradaException;

    List<Obra> getAllObrasByTipo(TipoObra tipoObraId);

    Obra getObraById(Integer id) throws ObraNoEncontradaException;

    List<Obra> getObrasByClienteId(Integer clienteId) throws ClienteNoEncontradoException, ObrasNoAsociadasException;

    List<Obra> getObrasByClienteCuit(String clienteCuit) throws ClienteNoEncontradoException, ObrasNoAsociadasException;

    Double getSaldoPorObrasByClient(Integer obraId) throws ObraNoEncontradaException;
}
