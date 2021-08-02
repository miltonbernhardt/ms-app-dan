package com.brikton.labapps.mscuentacorriente.service.impl;

import com.brikton.labapps.mscuentacorriente.dto.Obra;
import com.brikton.labapps.mscuentacorriente.dto.Pedido;
import com.brikton.labapps.mscuentacorriente.service.PedidoService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private RestTemplate restTemplate = new RestTemplate();

    private final String urlServer = "http://localhost";
    private final String puerto = "9002";
    private final String apiCliente = "api/pedido";

    @Override
    public List<Pedido> getPedidos(Obra obra) {
        return null;
    }
}
