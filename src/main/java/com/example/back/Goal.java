package com.example.back;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String targetTime;
    private String color;
    private LocalDate createdAt;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(String targetTime) {
        this.targetTime = targetTime;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    // Constructors

    public Goal() {
    }

    public Goal(String name, String targetTime, String color, LocalDate createdAt) {
        this.name = name;
        this.targetTime = targetTime;
        this.color = color;
        this.createdAt = createdAt;
    }
}

