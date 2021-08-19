package com.brikton.labapps.servicegateway.impl;

import com.brikton.labapps.servicegateway.domain.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${usuario.host}")
    private String host;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        String url = "http://localhost/api/usuario/" + mail;//TODO change for feign
        ResponseEntity<Usuario> respuesta = restTemplate.exchange(url, HttpMethod.GET, null, Usuario.class);

        if (respuesta.getStatusCode() == HttpStatus.OK) {
            return UsuarioDetailsImpl.build(Objects.requireNonNull(respuesta.getBody()));
        } else {
            if (respuesta.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new UsernameNotFoundException("No existe un usuario con ese username/mail");
            } else {
                url = "http://" + host + ":9000/api/usuario/" + mail;//TODO change for feign
                respuesta = restTemplate.exchange(url, HttpMethod.GET, null, Usuario.class);
                if (respuesta.getStatusCode() == HttpStatus.OK) {
                    return UsuarioDetailsImpl.build(Objects.requireNonNull(respuesta.getBody()));
                } else {
                    if (respuesta.getStatusCode() == HttpStatus.NOT_FOUND) {
                        throw new UsernameNotFoundException("No existe un usuario con ese username/mail");
                    } else {
                        throw new UsernameNotFoundException("Hubo un problema. " + respuesta.getBody());
                    }
                }
            }
        }

    }

}
