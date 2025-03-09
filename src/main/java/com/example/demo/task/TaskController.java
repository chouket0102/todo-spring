package com.example.demo.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Get all tasks
    @GetMapping
    public List<Task> getTasks() {
        return taskService.getTasks();
    }


    @PostMapping
    public ResponseEntity<String> addNewTask(@RequestBody Task task) {
        try {
            taskService.addNewTask(task);
            return new ResponseEntity<>("Task added successfully", HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
        try {
            taskService.deleteTask(taskId);
            return new ResponseEntity<>("Task deleted successfully", HttpStatus.NO_CONTENT);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<String> updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        try {
            taskService.updateTask(taskId, updatedTask);
            return new ResponseEntity<>("Task updated successfully", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/search")
    public List<Task> searchTasks(@RequestParam String taskContent) {
        return taskService.searchTasks(taskContent);
    }
}
