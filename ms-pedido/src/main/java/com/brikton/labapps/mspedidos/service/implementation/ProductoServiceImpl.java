package com.brikton.labapps.mspedidos.service.implementation;

import com.brikton.labapps.mspedidos.domain.Producto;
import com.brikton.labapps.mspedidos.exception.RecursoNoEncontradoException;
import com.brikton.labapps.mspedidos.service.ProductoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductoServiceImpl implements ProductoService {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${producto.host}")
    private String host;

    @Override
    public Integer getStockDisponible(Producto producto) throws RecursoNoEncontradoException {
        String url = "http://" + host + ":9001/api/producto/stock/" + producto.getId();//TODO change for feign
        // ResponseEntity<Integer> respuesta = restTemplate.exchange(server+"/stock?idProducto="+producto.getId(), HttpMethod.GET, null , Integer .class);
        ResponseEntity<Integer> respuesta = restTemplate.exchange(url, HttpMethod.GET, null, Integer.class);

        if (respuesta.getStatusCode() == HttpStatus.OK)
            return respuesta.getBody();
        else
            throw new RecursoNoEncontradoException("No se obtuvo respuesta con stock", 0);
    }

}
