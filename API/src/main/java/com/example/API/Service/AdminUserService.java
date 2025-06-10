package com.example.API.Service;

import com.example.API.Entity.AdminUser;
import com.example.API.Repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminUserService {

    @Autowired
    private AdminUserRepository repository;

    public AdminUser login(String username, String password) {
        Optional<AdminUser> userOpt = repository.findByUsername(username);
        if (userOpt.isPresent()) {
            AdminUser user = userOpt.get();
            if (user.getPassword().equals(password)) {
                return user; // Đăng nhập thành công
            }
        }
        throw new RuntimeException("Sai tên đăng nhập hoặc mật khẩu");
    }
    public AdminUser getByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    public AdminUser save(AdminUser adminUser) {
        return repository.save(adminUser);
    }

    public AdminUser getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public Iterable<AdminUser> getAll() {
        return repository.findAll();
    }
}
