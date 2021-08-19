package com.brikton.labapps.servicegateway.controller;

import com.brikton.labapps.servicegateway.domain.JwtResponse;
import com.brikton.labapps.servicegateway.domain.LoginRequest;
import com.brikton.labapps.servicegateway.domain.TipoUsuario;
import com.brikton.labapps.servicegateway.domain.Usuario;
import com.brikton.labapps.servicegateway.jwt.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public UserController(
            AuthenticationManager authenticationManager,
            JwtUtils jwtUtils
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getMail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        Usuario userDetails = (Usuario) authentication.getPrincipal();
        TipoUsuario rol = userDetails.getTipoUsuario();

        return ResponseEntity.ok(
                new JwtResponse(
                        jwt,
                        userDetails.getId(),
                        userDetails.getMail(),
                        rol
                )
        );
    }
}
