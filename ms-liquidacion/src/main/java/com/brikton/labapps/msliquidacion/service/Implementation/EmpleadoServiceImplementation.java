package com.brikton.labapps.msliquidacion.service.Implementation;

import com.brikton.labapps.msliquidacion.domain.Empleado;
import com.brikton.labapps.msliquidacion.service.EmpleadoService;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmpleadoServiceImplementation implements EmpleadoService {

  private RestTemplate restTemplate = new RestTemplate();

  private final String urlServer = "http://localhost";
  private final String apiEmpleado = "api/empleado";
  private final String puerto = "9000";

  @Override
  public List<Empleado> getEmpleados() throws Exception {
    String server = urlServer + ":" + puerto + "/" + apiEmpleado;
    ResponseEntity<List<Empleado>> respuesta = restTemplate.exchange(
      server,
      HttpMethod.GET,
      null,
      new ParameterizedTypeReference<List<Empleado>>() {}
    );
    if (
      respuesta.getStatusCode() == HttpStatus.OK
    ) return respuesta.getBody(); else throw new Exception(
      "No se obtuvo respuesta con empleados"
    );
  }
}
