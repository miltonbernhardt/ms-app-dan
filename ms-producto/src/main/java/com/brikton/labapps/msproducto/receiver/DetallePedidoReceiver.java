package com.brikton.labapps.msproducto.receiver;

import java.util.HashMap;

import com.brikton.labapps.msproducto.domain.Material;
import com.brikton.labapps.msproducto.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class DetallePedidoReceiver {
    
    @Autowired
    ProductoService service;

    @JmsListener(destination = "COLA_PEDIDOS")
    public void handle(HashMap<String,Integer> detalle) {
        try {
            Material m = service.getMaterial(detalle.get("idMaterial"));
            m.setStockActual(m.getStockActual()-detalle.get("cantidad"));
            service.update(m);    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
