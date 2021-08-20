package com.brikton.labapps.servicegateway.controller;

import com.brikton.labapps.servicegateway.domain.*;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder encoder;

    public UserController(
            AuthenticationManager authenticationManager,
            JwtUtils jwtUtils,
            PasswordEncoder encoder
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getMail(),
                            loginRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No se pudo autenticar el usuario. Verifique la contraseña y/o el username/email");
        } catch (InternalAuthenticationServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error");
        }


        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UsuarioDetailsImpl userDetails = (UsuarioDetailsImpl) authentication.getPrincipal();




//        TipoUsuario rol = userDetails.getTipoUsuario();


        return ResponseEntity.ok(
                new JwtResponse(
                        jwt,
                        userDetails.getMail(),
                        userDetails.getRol()
                )
        );
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
//        if (usuarioService.existsByUsername(signUpRequest.getMail())) {
//            return ResponseEntity.badRequest().body("Error: el username/email ya está en uso.");
//        }

//        TipoUsuario tipoUsuario;
//        try {
//            tipoUsuario = TipoUsuario.valueOf(signUpRequest.getTipoUsuario());
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El tipo de obra que se requiere no es válido. \nLos siguientes valores son válidos: \"REFORMA, CASA, EDIFICIO y VIAL\".");
//        }

        TipoUsuario tipoUsuario;
        try {
            tipoUsuario = TipoUsuario.valueOf(signUpRequest.getTipoUsuario());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El tipo de usuario que se requiere no es válido. \nLos siguientes valores son válidos: \"CLIENTE y EMPLEADO\".");
        }

        Usuario user = new Usuario(signUpRequest.getMail(), encoder.encode(signUpRequest.getPassword()), tipoUsuario);

//        try {
//            usuarioService.saveUser(user);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }

        return ResponseEntity.ok(user);
    }
}
