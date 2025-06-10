package com.example.API.Service;


import com.example.API.Entity.Schedule;
import com.example.API.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository repository;

    public List<Schedule> getAll() {
        return repository.findAll();
    }

    public Schedule getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Schedule save(Schedule s) {
        return repository.save(s);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}