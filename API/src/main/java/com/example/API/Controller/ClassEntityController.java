package com.example.API.Controller;
import com.example.API.Entity.*;
import com.example.API.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/classes")
public class ClassEntityController {
    @Autowired
    private ClassEntityService service;

    @GetMapping
    public List<ClassEntity> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ClassEntity getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public ClassEntity save(@RequestBody ClassEntity c) {
        return service.save(c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}

