package com.brikton.labapps.mspedidos.service;

import java.util.List;

import com.brikton.labapps.mspedidos.domain.Obra;
import com.brikton.labapps.mspedidos.exception.RecursoNoEncontradoException;

public interface ClienteService {
    
    public Double deudaCliente(Obra obra) throws RecursoNoEncontradoException;

    public List<Obra> getObrasCliente(Integer idCliente) throws RecursoNoEncontradoException;
    
}
