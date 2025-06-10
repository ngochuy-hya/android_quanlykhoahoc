package com.example.API.Service;

import com.example.API.Entity.TrainingProgram;
import com.example.API.Repository.TrainingProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingProgramService {
    @Autowired
    private TrainingProgramRepository repository;

    public List<TrainingProgram> getAll() {
        return repository.findAll();
    }

    public TrainingProgram getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public TrainingProgram save(TrainingProgram tp) {
        return repository.save(tp);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
