package com.example.qlsv.Entity;

import java.util.List;

public class Teacher {
    private Integer id;
    private String fullName;
    private String specialization;
    private String phone;
    private String email;
    private List<ClassEntity> assignedClasses;

    public Teacher() {}

    public Teacher(Integer id, String fullName, String specialization, String phone, String email, List<ClassEntity> assignedClasses) {
        this.id = id;
        this.fullName = fullName;
        this.specialization = specialization;
        this.phone = phone;
        this.email = email;
        this.assignedClasses = assignedClasses;
    }

    public Teacher(String fullName, String specialization, String phone, String email) {
        this.fullName = fullName;
        this.specialization = specialization;
        this.phone = phone;
        this.email = email;
    }

    // Getter – Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<ClassEntity> getAssignedClasses() { return assignedClasses; }
    public void setAssignedClasses(List<ClassEntity> assignedClasses) { this.assignedClasses = assignedClasses; }
}