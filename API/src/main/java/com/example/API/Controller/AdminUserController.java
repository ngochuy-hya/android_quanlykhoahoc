package com.example.API.Controller;
import com.example.API.Entity.*;
import com.example.API.Service.*;
import com.example.API.DTO.Request.ChangePasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/admin-users")
public class AdminUserController {
    @Autowired
    private AdminUserService service;

    @GetMapping
    public Iterable<AdminUser> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AdminUser getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public AdminUser save(@RequestBody AdminUser a) {
        return service.save(a);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public AdminUser updateInfo(@PathVariable int id, @RequestBody AdminUser updated) {
        System.out.println("Received update request for admin ID: " + id);
        System.out.println("Updated data: " + updated.toString());
        
        // Log authentication info
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Current authentication: " + (auth != null ? auth.getName() : "null"));
        System.out.println("Is authenticated: " + (auth != null && auth.isAuthenticated()));
        
        AdminUser admin = service.getById(id);
        if (admin == null) {
            System.out.println("Admin not found with ID: " + id);
            throw new RuntimeException("Admin not found");
        }
        
        // Log current admin data
        System.out.println("Current admin data: " + admin.toString());
        
        // Update fields
        admin.setFullName(updated.getFullName());
        admin.setEmail(updated.getEmail());
        admin.setAvatarUrl(updated.getAvatarUrl());
        
        // Only update password if it's provided and not empty
        if (updated.getPassword() != null && !updated.getPassword().trim().isEmpty()) {
            System.out.println("Updating password for admin ID: " + id);
            admin.setPassword(updated.getPassword());
        } else {
            System.out.println("Keeping existing password for admin ID: " + id);
        }
        
        AdminUser saved = service.save(admin);
        System.out.println("Updated admin data: " + saved.toString());
        return saved;
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<?> changePassword(@PathVariable int id, @RequestBody ChangePasswordRequest req) {
        AdminUser admin = service.getById(id);
        if (admin == null) return ResponseEntity.status(404).body(new ApiResponse("Admin not found"));
        if (!admin.getPassword().equals(req.getOldPassword())) {
            return ResponseEntity.badRequest().body(new ApiResponse("Mật khẩu cũ không đúng!"));
        }
        if (req.getOldPassword().equals(req.getNewPassword())) {
            return ResponseEntity.badRequest().body(new ApiResponse("Mật khẩu mới không được trùng với mật khẩu cũ!"));
        }
        admin.setPassword(req.getNewPassword());
        service.save(admin);
        return ResponseEntity.ok(new ApiResponse("Đổi mật khẩu thành công!"));
    }
}
