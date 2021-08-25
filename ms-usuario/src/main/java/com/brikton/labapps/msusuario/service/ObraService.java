package com.brikton.labapps.msusuario.service;

import com.brikton.labapps.msusuario.domain.Obra;
import com.brikton.labapps.msusuario.domain.TipoObra;

import java.util.List;
import java.util.Optional;

public interface ObraService {

    Obra saveObra(Obra obra);

    Obra updateObra(Obra nueva, Integer id) throws Exception;

    void deleteObra(Integer id) throws Exception;

    List<Obra> getAllObrasByTipo(TipoObra tipoObraId);

    Optional<Obra> getObraById(Integer id) throws Exception;

    List<Obra> getObrasByClienteId(Integer clienteId) throws Exception;

    List<Obra> getObrasByClienteCuit(String clienteCuit) throws Exception;

    Double getSaldoPorObrasByClient(Integer obraId) throws Exception;
}
