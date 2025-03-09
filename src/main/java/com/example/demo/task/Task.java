package com.example.demo.task;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Task {
    @Id
    @SequenceGenerator(
            name="task_sequence",
            sequenceName="task_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"

    )
    private Long id;
    private String task;
    private String createdAt;

    public Task() {}
    public Task(String task, String createdAt) {
        this.task = task;
        this.createdAt = createdAt;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task = task;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", content='" + task + '\'' +
                ", createdAt='" + createdAt + '\'' +

                '}';

    }
}
