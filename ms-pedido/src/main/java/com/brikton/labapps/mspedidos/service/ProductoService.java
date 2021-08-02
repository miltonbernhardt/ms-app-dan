package com.brikton.labapps.mspedidos.service;

import com.brikton.labapps.mspedidos.domain.Producto;
import com.brikton.labapps.mspedidos.exception.RecursoNoEncontradoException;


public interface ProductoService {

   public Integer stockDisponible(Producto producto) throws RecursoNoEncontradoException;
    
}
