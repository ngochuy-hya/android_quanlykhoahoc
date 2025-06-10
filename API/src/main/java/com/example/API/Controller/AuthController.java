package com.example.API.Controller;

import com.example.API.DTO.Reponse.LoginResponse;
import com.example.API.DTO.Request.ForgotPasswordRequest;
import com.example.API.DTO.Request.LoginRequest;
import com.example.API.DTO.Request.ResetPasswordRequest;
import com.example.API.Entity.AdminUser;
import com.example.API.Repository.AdminUserRepository;
import com.example.API.Security.JwtUtil;
import com.example.API.Service.AdminUserService;
import com.example.API.Service.PasswordResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AdminUserService adminUserService;
    private final PasswordResetTokenService tokenService;
    public AuthController(PasswordResetTokenService tokenService) {
        this.tokenService = tokenService;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        AdminUser user = adminUserRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại"));

        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai mật khẩu");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(new LoginResponse(token));
    }
    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestBody ForgotPasswordRequest request) {
        return tokenService.generateAndSendOTP(request.getEmail());
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody ResetPasswordRequest request) {
        return tokenService.resetPassword(request.getEmail(), request.getOtp(), request.getNewPassword());
    }
}