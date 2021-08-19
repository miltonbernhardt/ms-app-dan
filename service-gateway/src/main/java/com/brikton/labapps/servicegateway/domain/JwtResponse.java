package com.brikton.labapps.servicegateway.domain;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String mail;
    private TipoUsuario rol;

    public JwtResponse(String accessToken, Long id, String email, TipoUsuario rol) {
        this.token = accessToken;
        this.id = id;
        this.mail = email;
        this.rol = rol;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public TipoUsuario getRol() {
        return rol;
    }
}
