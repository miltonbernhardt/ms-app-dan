package com.brikton.labapps.mscuentacorriente.service.impl;

import com.brikton.labapps.mscuentacorriente.domain.Cliente;
import com.brikton.labapps.mscuentacorriente.dto.Obra;
import com.brikton.labapps.mscuentacorriente.repository.ClienteRepository;
import com.brikton.labapps.mscuentacorriente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${usuario.host}")
    private String host;

    @Override
    public Cliente getClienteCorrecto(Cliente cliente) throws Exception {
        String url = "http://" + host + ":9000/api/cliente/cuit/" + cliente.getCuit();//TODO change for feign

        Optional<Cliente> clienteFromRepo = Optional.ofNullable(clienteRepository.getClienteByCuit(cliente.getCuit()));
        if (clienteFromRepo.isEmpty()) {
            ResponseEntity<Cliente> clienteRespuesta;
            try {
                clienteRespuesta = restTemplate.exchange(url, HttpMethod.GET, null, Cliente.class);
                return clienteRepository.save(Objects.requireNonNull(clienteRespuesta.getBody()));
            } catch (final HttpClientErrorException e) {
                System.out.println(e.getResponseBodyAsString());
                throw new Exception("Debe registrar el pago de un cliente previamente registrado.");
            }
        }

        return clienteFromRepo.orElseGet(() -> clienteRepository.save(cliente));
    }

    @Override
    public List<Obra> getObras(Cliente cliente) throws Exception {
        String url = "http://" + host + ":9000/api/obra/obrasPorCliente/cuit/" + cliente.getCuit();//TODO change for feign

        ResponseEntity<List<Obra>> obras;
        try {
            obras = restTemplate.exchange(url, HttpMethod.GET, null, (Class<List<Obra>>) (Object) List.class);

        } catch (final HttpClientErrorException e) {
            System.out.println(e.getResponseBodyAsString());
            throw new Exception("El cliente no tiene registradas obras a su haber.");
        }
        return obras.getBody();
    }
}
