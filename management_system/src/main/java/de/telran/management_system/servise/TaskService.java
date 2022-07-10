package de.telran.management_system.servise;

import de.telran.management_system.dto.TaskRequestDTO;
import de.telran.management_system.dto.TaskResponseDTO;
import de.telran.management_system.entity.types.TaskStatus;

import java.util.List;

public interface TaskService {

    void createTask(TaskRequestDTO request, Long projectId);

    void pushTaskForward(Long projectId, Long taskId);

    void pushTaskBackward(Long projectId, Long taskId);

    TaskResponseDTO findByName(Long projectId, String name);

    List<TaskResponseDTO> findByStatus(Long projectId, TaskStatus status);

    List<TaskResponseDTO> findAbandonedTasks(Long projectId);

    List<TaskResponseDTO> findAll(Long projectId);
}