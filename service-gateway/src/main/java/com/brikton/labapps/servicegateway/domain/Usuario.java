package com.brikton.labapps.servicegateway.domain;

public class Usuario {

    private Long id;
    private String username;
    private String password;
    private TipoUsuario tipoUsuario;

    public Usuario() {

    }

    public Usuario(String username, String password, TipoUsuario tipoUsuario) {
        this.username = username;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase().replaceAll("\\s+", "");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{\n" +
                " id=" + id +
                ",\n username='" + username + '\'' +
                ",\n password='" + password + '\'' +
                ",\n tipoUsuario=" + tipoUsuario +
                "\n}";
    }
}
