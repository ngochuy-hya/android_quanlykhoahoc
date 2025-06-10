package com.example.API.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String duration;

    @OneToMany(mappedBy = "trainingProgram", cascade = CascadeType.ALL)
    private List<ClassEntity> classes;

    @OneToMany(mappedBy = "trainingProgram", cascade = CascadeType.ALL)
    private List<Schedule> schedules;

    public TrainingProgram(String name, String description, String duration) {
        this.name = name;
        this.description = description;
        this.duration = duration;
    }
}
