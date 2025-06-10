package com.example.API.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String studentCode;

    private String fullName;
    private LocalDate dob;
    private String address;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;

    public Student(String studentCode, String fullName, LocalDate dob, String address, ClassEntity classEntity) {
        this.studentCode = studentCode;
        this.fullName = fullName;
        this.dob = dob;
        this.address = address;
        this.classEntity = classEntity;
    }
}
