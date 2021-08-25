package com.brikton.labapps.mscuentacorriente.service.impl;

import com.brikton.labapps.mscuentacorriente.dto.Obra;
import com.brikton.labapps.mscuentacorriente.dto.Pedido;
import com.brikton.labapps.mscuentacorriente.service.PedidoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Value("${pedido.host}")
    private String host;

    @Override
    public List<Pedido> getPedidos(Obra obra) {
        //TODO implementar esto
        String url = "http://" + host + ":9002/api/pedido"; //TODO change for feign
        return defaultPedidos();
    }

    public List<Pedido> defaultPedidos() {
        return new ArrayList<>();
    }
}
