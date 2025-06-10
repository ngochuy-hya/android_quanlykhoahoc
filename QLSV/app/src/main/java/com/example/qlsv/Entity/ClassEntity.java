package com.example.qlsv.Entity;

import java.util.List;

public class ClassEntity {
    private Integer id;
    private String name;
    private TrainingProgram trainingProgram;
    private Teacher teacher;
    private List<Student> students;

    public ClassEntity() {}

    public ClassEntity(Integer id, String name, TrainingProgram trainingProgram, Teacher teacher, List<Student> students) {
        this.id = id;
        this.name = name;
        this.trainingProgram = trainingProgram;
        this.teacher = teacher;
        this.students = students;
    }

    public ClassEntity(String name, TrainingProgram trainingProgram, Teacher teacher) {
        this.name = name;
        this.trainingProgram = trainingProgram;
        this.teacher = teacher;
    }

    // Getter – Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public TrainingProgram getTrainingProgram() { return trainingProgram; }
    public void setTrainingProgram(TrainingProgram trainingProgram) { this.trainingProgram = trainingProgram; }

    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }

    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { this.students = students; }
}