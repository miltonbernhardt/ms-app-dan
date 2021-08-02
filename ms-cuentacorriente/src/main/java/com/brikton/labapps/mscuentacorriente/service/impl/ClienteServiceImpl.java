package com.brikton.labapps.mscuentacorriente.service.impl;

import com.brikton.labapps.mscuentacorriente.domain.Cliente;
import com.brikton.labapps.mscuentacorriente.dto.Obra;
import com.brikton.labapps.mscuentacorriente.repository.ClienteRepository;
import com.brikton.labapps.mscuentacorriente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final String urlServer = "http://localhost";
    private final String puerto = "9000";
    private final String apiObra = "api/obra";
    private final String apiCliente = "api/cliente";

    @Override
    public Cliente getClienteCorrecto(Cliente cliente) throws Exception {

        Optional<Cliente> clienteFromRepo = Optional.ofNullable(clienteRepository.getClienteByCuit(cliente.getCuit()));
        if (clienteFromRepo.isEmpty()) {
            String server = urlServer + ":" + puerto + "/" + apiCliente;
            ResponseEntity<Cliente> clienteRespuesta;
            try {
                clienteRespuesta = restTemplate.exchange(server + "/cuit/" + cliente.getCuit(), HttpMethod.GET, null, Cliente.class);
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
        String server = urlServer + ":" + puerto + "/" + apiObra + "/obrasPorCliente/cuit/" + cliente.getCuit();
        ResponseEntity<List<Obra>> obras;
        try {
            obras = restTemplate.exchange(server, HttpMethod.GET, null, (Class<List<Obra>>) (Object) List.class);

        } catch (final HttpClientErrorException e) {
            System.out.println(e.getResponseBodyAsString());
            throw new Exception("El cliente no tiene registradas obras a su haber.");
        }
        return obras.getBody();
    }
}
