package com.example.API.Service;

import com.example.API.Entity.Student;
import com.example.API.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Student> search(String keyword) {
        return repository.findByFullNameContainingOrStudentCodeContainingOrClassEntity_NameContaining(keyword, keyword, keyword);
    }

    public Student save(Student s) {
        return repository.save(s);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
