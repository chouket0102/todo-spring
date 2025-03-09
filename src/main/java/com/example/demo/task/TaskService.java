package com.example.demo.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Get all tasks
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }


    public void addNewTask(Task task) {

        List<Task> existingTasks = taskRepository.findByTaskContainingIgnoreCase(task.getTask());
        if (!existingTasks.isEmpty()) {
            throw new IllegalStateException("Task already exists");
        }
        taskRepository.save(task);
    }


    public void deleteTask(Long taskId) {
        boolean exists = taskRepository.existsById(taskId);
        if (!exists) {
            throw new IllegalStateException("Task does not exist");
        }
        taskRepository.deleteById(taskId);
    }


    @Transactional
    public Task updateTask(Long taskId, Task updatedTask) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalStateException("Task with ID " + taskId + " does not exist"));


        if (updatedTask.getTask() != null && !updatedTask.getTask().equals(existingTask.getTask())) {
            existingTask.setTask(updatedTask.getTask());
        }


        if (updatedTask.getCreatedAt() != null && !updatedTask.getCreatedAt().equals(existingTask.getCreatedAt())) {
            existingTask.setCreatedAt(updatedTask.getCreatedAt());
        }

        return existingTask;
    }


    public List<Task> searchTasks(String taskContent) {
        return taskRepository.findByTaskContainingIgnoreCase(taskContent);
    }
}
