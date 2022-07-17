package de.telran.management_system.servise.impl;

import de.telran.management_system.dto.ProjectRequestDTO;
import de.telran.management_system.dto.ProjectResponseDTO;
import de.telran.management_system.dto.TaskRequestDTO;
import de.telran.management_system.dto.TaskResponseDTO;
import de.telran.management_system.entity.Project;
import de.telran.management_system.entity.Task;
import de.telran.management_system.entity.types.TaskStatus;
import de.telran.management_system.repository.ProjectRepository;
import de.telran.management_system.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {
    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ProjectRepository projectRepository;
    @InjectMocks
    ProjectServiceImpl service;

    @Test
    @DisplayName("should save() project")
    public void shouldSaveprojectTest() {
        ProjectRequestDTO request = ProjectRequestDTO.builder()
                .projectName("CABAS")
                .build();
        Project project = Project.builder()
                .projectName(request.getProjectName())
                .build();
        Mockito
                .when(projectRepository.save(
                        ArgumentMatchers.any()))
                .thenReturn(project);

        service.createProject(request);
    }

    @Nested
    @DisplayName("findAll()")
    class findAll {


        @Test
        @DisplayName("should  find all throw 404-NOT_FOUND, when no such project")
        public void findAllShouldThrow404WhenNoSuchProjectTest() {

            Long id = 1L;
            int pageSize = 2;
            int pageNumber = 1;

            HttpStatus expectedStatus = HttpStatus.NOT_FOUND;
            Mockito
                    .when(projectRepository.findById(id))
                    .thenReturn(Optional.empty());
            ResponseStatusException ex = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> service.findAll(id, pageSize, pageNumber)
            );
            Assertions.assertEquals(expectedStatus, ex.getStatus());
        }


    }
}