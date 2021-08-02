package com.brikton.labapps.mspedidos.service.implementation;

import com.brikton.labapps.mspedidos.domain.Producto;
import com.brikton.labapps.mspedidos.exception.RecursoNoEncontradoException;
import com.brikton.labapps.mspedidos.service.ProductoService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductoServiceImpl implements ProductoService {

    private RestTemplate restTemplate = new RestTemplate();

    private final String urlServer = "http://localhost";
    private final String apiProducto = "api/producto";
    private final String puerto = "9001";

    @Override
    public Integer stockDisponible(Producto producto) throws RecursoNoEncontradoException {
        // TODO ver con kton
        String server = urlServer + ":" + puerto + "/" + apiProducto;
        // ResponseEntity<Integer> respuesta = restTemplate.exchange(server+"/stock?idProducto="+producto.getId(), HttpMethod.GET, null , Integer .class);
        ResponseEntity<Integer> respuesta = restTemplate.exchange(server + "/stock/" + producto.getId(), HttpMethod.GET, null, Integer.class);
        if (respuesta.getStatusCode() == HttpStatus.OK) return respuesta.getBody();
        else throw new RecursoNoEncontradoException("No se obtuvo respuesta con stock", 0);
    }

}
