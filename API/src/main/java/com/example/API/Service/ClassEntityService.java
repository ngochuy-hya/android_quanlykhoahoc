package com.example.API.Service;

import com.example.API.Entity.ClassEntity;
import com.example.API.Repository.ClassEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassEntityService {
    @Autowired
    private ClassEntityRepository repository;

    public List<ClassEntity> getAll() {
        return repository.findAll();
    }

    public ClassEntity getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public ClassEntity save(ClassEntity c) {
        return repository.save(c);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}