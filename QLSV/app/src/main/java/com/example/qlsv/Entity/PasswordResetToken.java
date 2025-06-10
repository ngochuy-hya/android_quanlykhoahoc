package com.example.qlsv.Entity;

import java.time.LocalDateTime;

public class PasswordResetToken {
    private Integer id;
    private String email;
    private String token;
    private LocalDateTime expiry;
    private LocalDateTime createdAt;

    public PasswordResetToken() {}

    public PasswordResetToken(Integer id, String email, String token, LocalDateTime expiry, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.token = token;
        this.expiry = expiry;
        this.createdAt = createdAt;
    }

    public PasswordResetToken(String email, String token, LocalDateTime expiry) {
        this.email = email;
        this.token = token;
        this.expiry = expiry;
    }

    // Getter – Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public LocalDateTime getExpiry() { return expiry; }
    public void setExpiry(LocalDateTime expiry) { this.expiry = expiry; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}