package com.brikton.labapps.mscuentacorriente.service;

import com.brikton.labapps.mscuentacorriente.domain.Cliente;
import com.brikton.labapps.mscuentacorriente.dto.Obra;

import java.util.List;

public interface ClienteService {

    Cliente getClienteCorrecto(Cliente cliente) throws Exception;

    List<Obra> getObras(Cliente cliente) throws Exception;
}
