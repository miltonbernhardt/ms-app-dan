package com.brikton.labapps.servicegateway.domain;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private TipoUsuario rol;

    public JwtResponse(String accessToken, String username, TipoUsuario rol) {
        this.token = accessToken;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public TipoUsuario getRol() {
        return rol;
    }
}
