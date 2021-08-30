package com.brikton.labapps.msliquidacion.service.Implementation;

import com.brikton.labapps.msliquidacion.domain.Empleado;
import com.brikton.labapps.msliquidacion.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmpleadoServiceImplementation implements EmpleadoService {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${usuario.host}")
    private String host;

    @Override
    public List<Empleado> getEmpleados() throws Exception {
        String url = "http://" + host + ":9000/api/empleado";
        ResponseEntity<List<Empleado>> respuesta = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        if (respuesta.getStatusCode() == HttpStatus.OK)
            return respuesta.getBody();
        else
            throw new Exception("No se obtuvo respuesta con empleados");
    }
}
