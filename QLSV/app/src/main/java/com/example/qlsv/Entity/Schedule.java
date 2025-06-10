package com.example.qlsv.Entity;

import java.time.LocalDate;

public class Schedule {
    private Integer id;
    private LocalDate startDate;
    private String lessonPlan;
    private TrainingProgram trainingProgram;

    public Schedule() {}

    public Schedule(Integer id, LocalDate startDate, String lessonPlan, TrainingProgram trainingProgram) {
        this.id = id;
        this.startDate = startDate;
        this.lessonPlan = lessonPlan;
        this.trainingProgram = trainingProgram;
    }

    public Schedule(LocalDate startDate, String lessonPlan, TrainingProgram trainingProgram) {
        this.startDate = startDate;
        this.lessonPlan = lessonPlan;
        this.trainingProgram = trainingProgram;
    }

    // Getter – Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public String getLessonPlan() { return lessonPlan; }
    public void setLessonPlan(String lessonPlan) { this.lessonPlan = lessonPlan; }

    public TrainingProgram getTrainingProgram() { return trainingProgram; }
    public void setTrainingProgram(TrainingProgram trainingProgram) { this.trainingProgram = trainingProgram; }
}