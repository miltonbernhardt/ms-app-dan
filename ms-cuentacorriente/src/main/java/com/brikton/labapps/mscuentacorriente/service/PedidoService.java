package com.brikton.labapps.mscuentacorriente.service;

import com.brikton.labapps.mscuentacorriente.dto.Obra;
import com.brikton.labapps.mscuentacorriente.dto.Pedido;

import java.util.List;

public interface PedidoService {
    List<Pedido> getPedidos(Obra obra);
}
