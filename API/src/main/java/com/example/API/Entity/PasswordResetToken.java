package com.example.API.Entity;

import jakarta.persistence.PrePersist;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    private String token;
    private LocalDateTime expiry;
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public PasswordResetToken(String email, String token, LocalDateTime expiry) {
        this.email = email;
        this.token = token;
        this.expiry = expiry;
    }
}
