package com.example.API.Controller;
import com.example.API.Entity.*;
import com.example.API.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    @Autowired
    private ScheduleService service;

    @GetMapping
    public List<Schedule> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Schedule getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public Schedule save(@RequestBody Schedule s) {
        return service.save(s);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
