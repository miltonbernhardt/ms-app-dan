package com.brikton.labapps.servicegateway.domain;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String mail;
    private TipoUsuario rol;

    public JwtResponse(String accessToken, String email, TipoUsuario rol) {
        this.token = accessToken;
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
