package com.brikton.labapps.servicegateway.controller;

import com.brikton.labapps.servicegateway.domain.*;
import com.brikton.labapps.servicegateway.impl.UserDetailsServiceImpl;
import com.brikton.labapps.servicegateway.impl.UsuarioDetailsImpl;
import com.brikton.labapps.servicegateway.jwt.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
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
    private final UserDetailsServiceImpl userDetailsService;

    public UserController(
            AuthenticationManager authenticationManager,
            JwtUtils jwtUtils,
            UserDetailsServiceImpl userDetailsService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException | InternalAuthenticationServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo autenticar el usuario. Verifique la contraseña y/o el username/email");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UsuarioDetailsImpl userDetails = (UsuarioDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(
                new JwtResponse(jwt, userDetails.getUsername(), userDetails.getRol())
        );
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userDetailsService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Error: el username/email ya está en uso.");
        }

        TipoUsuario tipoUsuario;
        try {
            tipoUsuario = TipoUsuario.valueOf(signUpRequest.getTipoUsuario());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El tipo de usuario que se requiere no es válido. \nLos siguientes valores son válidos: \"CLIENTE y EMPLEADO\".");
        }

        Usuario user = new Usuario(signUpRequest.getUsername(), signUpRequest.getPassword(), tipoUsuario);

        try {
            userDetailsService.saveUser(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.ok(user);
    }

    //TODO hacer un logout
//    public String refreshToken(String token) {
//        final Date createdDate = new Date();
//        final Date expirationDate = calculateExpirationDate(createdDate);
//
//        final Claims claims = getAllClaimsFromToken(token);
//        claims.setIssuedAt(createdDate);
//        claims.setExpiration(expirationDate);
//
//        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
//    }
}
