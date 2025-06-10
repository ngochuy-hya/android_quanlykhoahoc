package com.example.qlsv.Service.DTO.Response;

import com.example.qlsv.Entity.AdminUser;

public class LoginResponse {
    private String token;
    private AdminUser admin;

    public String getToken() { return token; }
    public AdminUser getAdmin() { return admin; }
}
