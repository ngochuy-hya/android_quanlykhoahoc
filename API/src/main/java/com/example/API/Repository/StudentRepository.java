package com.example.API.Repository;

import com.example.API.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByFullNameContainingOrStudentCodeContainingOrClassEntity_NameContaining(String name, String code, String className);
}
