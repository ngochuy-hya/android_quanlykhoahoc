package com.example.qlsv.Entity;

import java.util.List;

public class TrainingProgram {
    private Integer id;
    private String name;
    private String description;
    private String duration;
    private List<ClassEntity> classes;
    private List<Schedule> schedules;

    public TrainingProgram() {}

    public TrainingProgram(Integer id, String name, String description, String duration,
                           List<ClassEntity> classes, List<Schedule> schedules) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.classes = classes;
        this.schedules = schedules;
    }

    public TrainingProgram(String name, String description, String duration) {
        this.name = name;
        this.description = description;
        this.duration = duration;
    }

    // Getter – Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public List<ClassEntity> getClasses() { return classes; }
    public void setClasses(List<ClassEntity> classes) { this.classes = classes; }

    public List<Schedule> getSchedules() { return schedules; }
    public void setSchedules(List<Schedule> schedules) { this.schedules = schedules; }
}
