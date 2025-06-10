package com.example.qlsv.Entity;

import java.time.LocalDate;

public class Student {
    private Integer id;
    private String studentCode;
    private String fullName;
    private LocalDate dob;
    private String address;
    private ClassEntity classEntity;

    public Student() {}

    public Student(Integer id, String studentCode, String fullName, LocalDate dob, String address, ClassEntity classEntity) {
        this.id = id;
        this.studentCode = studentCode;
        this.fullName = fullName;
        this.dob = dob;
        this.address = address;
        this.classEntity = classEntity;
    }

    public Student(String studentCode, String fullName, LocalDate dob, String address, ClassEntity classEntity) {
        this.studentCode = studentCode;
        this.fullName = fullName;
        this.dob = dob;
        this.address = address;
        this.classEntity = classEntity;
    }

    // Getter – Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getStudentCode() { return studentCode; }
    public void setStudentCode(String studentCode) { this.studentCode = studentCode; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public ClassEntity getClassEntity() { return classEntity; }
    public void setClassEntity(ClassEntity classEntity) { this.classEntity = classEntity; }
}