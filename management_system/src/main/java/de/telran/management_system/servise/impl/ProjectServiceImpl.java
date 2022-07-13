package de.telran.management_system.servise.impl;

import de.telran.management_system.dto.ProjectRequestDTO;
import de.telran.management_system.dto.ProjectResponseDTO;
import de.telran.management_system.dto.TaskResponseDTO;
import de.telran.management_system.entity.Project;
import de.telran.management_system.entity.Task;
import de.telran.management_system.repository.ProjectRepository;
import de.telran.management_system.repository.TaskRepository;
import de.telran.management_system.servise.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void createProject(ProjectRequestDTO request) {
        Project project = Project.builder()
                .projectName(request.getProjectName())
                .build();
        projectRepository.save(project);
    }

    @Override
    public ProjectResponseDTO findAll(Long id, int pageSize, int pageNumber) {
        Project project = projectRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        Pageable page = (Pageable) PageRequest.of(pageNumber, pageSize);
        List<Task> tasks = taskRepository.findAll((Sort) page);

        List<TaskResponseDTO> taskResponseDTOS = tasks.stream().map(x -> {
                    TaskResponseDTO taskResponseDTO = TaskResponseDTO.builder().
                            id(x.getId()).
                            taskName(x.getTaskName()).
                            daysToComplete(x.getDaysToComplete()).
                            taskStatus(x.getTaskStatus()).
                            createdOn(x.getCreatedOn()).
                            updatedOn(x.getUpdatedOn()).
                            projectId(project.getId()).
                            build();
                    return taskResponseDTO;
                }
        ).collect(Collectors.toList());

        return ProjectResponseDTO.builder().
                id(project.getId()).
                projectName(project.getProjectName()).
                createdOn(project.getCreatedOn()).
                updatedOn(project.getUpdatedOn()).
                tasks(taskResponseDTOS).
                build();
    }
}