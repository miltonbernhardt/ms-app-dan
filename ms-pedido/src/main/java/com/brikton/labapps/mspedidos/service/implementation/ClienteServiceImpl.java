package com.brikton.labapps.mspedidos.service.implementation;

import com.brikton.labapps.mspedidos.domain.Obra;
import com.brikton.labapps.mspedidos.exception.RecursoNoEncontradoException;
import com.brikton.labapps.mspedidos.service.ClienteService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${usuario.host}")
    private String host;

    @Override
    public Double deudaCliente(Obra obra) throws RecursoNoEncontradoException {
        String url = "http://" + host + ":9000/api/obra/saldo/" + obra.getId();//TODO change for feign
        ResponseEntity<Double> respuesta = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Double.class
        );

        if (respuesta.getStatusCode() == HttpStatus.OK)
            return respuesta.getBody();
        else
            throw new RecursoNoEncontradoException("No se obtuvo respuesta con saldo deudor", 0);
    }

    @Override
    public List<Obra> getObrasCliente(Integer idCliente) throws RecursoNoEncontradoException {
        String url = "http://" + host + ":9000/api/obra/obrasPorCliente/" + idCliente;//TODO change for feign
        ResponseEntity<List<Obra>> respuesta = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        if (respuesta.getStatusCode() == HttpStatus.OK)
            return respuesta.getBody();
        else
            throw new RecursoNoEncontradoException("No se obtuvo respuesta con obras de cliente", 0);
    }
}
