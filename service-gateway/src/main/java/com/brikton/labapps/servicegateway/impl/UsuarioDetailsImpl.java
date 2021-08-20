package com.brikton.labapps.servicegateway.impl;

import com.brikton.labapps.servicegateway.domain.TipoUsuario;
import com.brikton.labapps.servicegateway.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class UsuarioDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String mail;

    @JsonIgnore
    private String password;

    private TipoUsuario rol;

    public UsuarioDetailsImpl(Long id, String email, String password, TipoUsuario rol) {
        this.id = id;
        this.mail = email;
        this.password = password;
        this.rol = rol;
    }

    public static UsuarioDetailsImpl build(Usuario usuario) {
        return new UsuarioDetailsImpl(
                usuario.getId(),
                usuario.getMail(),
                usuario.getPassword(),
                usuario.getTipoUsuario()
        );
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UsuarioDetailsImpl user = (UsuarioDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUsuario getRol() {
        return rol;
    }

    public void setRol(TipoUsuario rol) {
        this.rol = rol;
    }
}