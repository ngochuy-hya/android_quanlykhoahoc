package com.example.API.Controller;
import com.example.API.Entity.*;
import com.example.API.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
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
}
