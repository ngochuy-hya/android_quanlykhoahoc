package com.example.API.Controller;

import com.example.API.Entity.TrainingProgram;
import com.example.API.Service.TrainingProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training-programs")
public class TrainingProgramController {
    @Autowired
    private TrainingProgramService service;

    @GetMapping
    public List<TrainingProgram> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public TrainingProgram getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public TrainingProgram create(@RequestBody TrainingProgram tp) {
        return service.save(tp);
    }

    @PutMapping("/{id}")
    public TrainingProgram update(@PathVariable int id, @RequestBody TrainingProgram tp) {
        tp.setId(id);
        return service.save(tp);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}