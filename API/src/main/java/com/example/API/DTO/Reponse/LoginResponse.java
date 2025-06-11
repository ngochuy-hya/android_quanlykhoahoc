package com.example.API.DTO.Reponse;

import com.example.API.Entity.*;

public class LoginResponse {
    private String token;
    private AdminUser admin;

    public LoginResponse(String token, AdminUser admin) {
        this.token = token;
        this.admin = admin;
    }

    public String getToken() { return token; }
    public AdminUser getAdmin() { return admin; }
}