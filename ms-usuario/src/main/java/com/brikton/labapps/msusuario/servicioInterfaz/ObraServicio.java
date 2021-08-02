package com.brikton.labapps.msusuario.servicioInterfaz;

import com.brikton.labapps.msusuario.domain.Obra;
import com.brikton.labapps.msusuario.domain.TipoObra;

import java.util.List;
import java.util.Optional;

public interface ObraServicio {

    List<Obra> listarObras(TipoObra tipoObraId);

    Optional<Obra> buscarObraPorId(Integer id) throws Exception;

    Obra guardarObra(Obra obra) throws Exception;

    List<Obra> listarObrasPorCliente(Integer clienteId) throws Exception;

    List<Obra> listarObrasPorCliente(String clienteCuit) throws Exception;

    Double buscarSaldoClienteDeObra(Integer obraId) throws Exception;

}
