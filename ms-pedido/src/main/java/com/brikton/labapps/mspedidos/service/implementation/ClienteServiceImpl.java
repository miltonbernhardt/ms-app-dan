package com.brikton.labapps.mspedidos.service.implementation;

import java.util.List;

import com.brikton.labapps.mspedidos.domain.Obra;
import com.brikton.labapps.mspedidos.exception.RecursoNoEncontradoException;
import com.brikton.labapps.mspedidos.service.ClienteService;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClienteServiceImpl implements ClienteService {

    private RestTemplate restTemplate = new RestTemplate();

	private final String urlServer= "http://localhost";
	private final String apiCliente = "api/cliente";
    private final String puerto = "9000";

    @Override
    public Double deudaCliente(Obra obra) throws RecursoNoEncontradoException {
        // TODO ver con tamara
        String server = urlServer+":"+puerto+"/api/obra/saldo/";
		ResponseEntity<Double> respuesta = restTemplate.exchange(server+"/"+obra.getId(), HttpMethod.GET, null , Double.class);
		if (respuesta.getStatusCode() == HttpStatus.OK) return respuesta.getBody();
        else throw new RecursoNoEncontradoException("No se obtuvo respuesta con saldo deudor",0);
    }

    @Override
    public List<Obra> getObrasCliente(Integer idCliente) throws RecursoNoEncontradoException {
        // TODO ver con tamara
        String server = urlServer+":"+puerto+"/api/obra/obrasPorCliente/";
		ResponseEntity<List<Obra>> respuesta = restTemplate.exchange(server+"/"+idCliente, HttpMethod.GET, null , 
        new ParameterizedTypeReference<List<Obra>>(){});
		if (respuesta.getStatusCode() == HttpStatus.OK) return respuesta.getBody();
        else throw new RecursoNoEncontradoException("No se obtuvo respuesta con obras de cliente",0);
    }
    // @Override
    // public Double deudaCliente(Obra obra) throws RecursoNoEncontradoException {
    //     // TODO ver con tamara
    //     return 10000.0;
    // }

    // @Override
    // public List<Obra> getObrasCliente(Integer idCliente) throws RecursoNoEncontradoException {
    //     // TODO ver con tamara
    // return null;
    // }
}
