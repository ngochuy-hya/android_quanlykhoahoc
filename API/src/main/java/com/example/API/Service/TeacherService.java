package com.example.API.Service;

import com.example.API.Entity.Teacher;
import com.example.API.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository repository;

    public List<Teacher> getAll() {
        return repository.findAll();
    }

    public Teacher getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Teacher save(Teacher t) {
        return repository.save(t);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}