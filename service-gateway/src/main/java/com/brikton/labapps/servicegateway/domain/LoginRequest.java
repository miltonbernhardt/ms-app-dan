package com.brikton.labapps.servicegateway.domain;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String mail;

    @NotBlank
    private String password;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
