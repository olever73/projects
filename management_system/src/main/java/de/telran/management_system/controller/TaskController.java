package de.telran.management_system.controller;


import de.telran.management_system.dto.TaskRequestDTO;
import de.telran.management_system.dto.TaskResponseDTO;
import de.telran.management_system.entity.types.TaskStatus;
import de.telran.management_system.servise.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/project/{projectId}/task")
    public void createTask(@RequestBody @Valid TaskRequestDTO request, @PathVariable("projectId") Long projectId) {
        taskService.createTask(request, projectId);
    }

    @PutMapping("/project/{projectId}/task/{taskId}/pushForward")
    public void pushTaskForward(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId) {
        taskService.pushTaskForward(projectId, taskId);
    }

    @PutMapping("/project/{projectId}/task/{taskId}/pushBackward")
    public void pushTaskBackward(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId) {
        taskService.pushTaskBackward(projectId, taskId);
    }

    @GetMapping("/project/{projectId}/name/{name}")
    public TaskResponseDTO findByName(@PathVariable("projectId") Long projectId, @PathVariable("name") String name) {
        return taskService.findByName(projectId, name);
    }

    @GetMapping("/project/{projectId}/status/{status}")
    public List<TaskResponseDTO> findByStatus(@PathVariable("projectId") Long projectId, @PathVariable("status") TaskStatus status) {
        return taskService.findByStatus(projectId, status);
    }

    @GetMapping("/project/{projectId}/abandoned")
    public List<TaskResponseDTO> findAbandonedTasks(@PathVariable("projectId") Long projectId) {
        return taskService.findAbandonedTasks(projectId);
    }

}