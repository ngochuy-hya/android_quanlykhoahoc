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
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate startDate;
    private String lessonPlan;

    @ManyToOne
    @JoinColumn(name = "training_program_id")
    private TrainingProgram trainingProgram;

    public Schedule(LocalDate startDate, String lessonPlan, TrainingProgram trainingProgram) {
        this.startDate = startDate;
        this.lessonPlan = lessonPlan;
        this.trainingProgram = trainingProgram;
    }
}
