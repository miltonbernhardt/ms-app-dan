package com.brikton.labapps.mscuentacorriente.service;

import com.brikton.labapps.mscuentacorriente.domain.Cliente;
import com.brikton.labapps.mscuentacorriente.domain.Pago;
import com.brikton.labapps.mscuentacorriente.dto.Pedido;

import java.util.List;

public interface CuentaCorrienteService {

    Pago save(Pago pagoNuevo) throws Exception;

    List<Pago> getDetallePagos(Cliente cliente) throws Exception;

    List<Pedido> getFacturas(Cliente cliente) throws Exception;
}
