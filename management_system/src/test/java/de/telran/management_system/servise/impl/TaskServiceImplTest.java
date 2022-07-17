package de.telran.management_system.servise.impl;

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
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.List.of;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {
    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ProjectRepository projectRepository;
    @InjectMocks
    TaskServiceImpl service;

    @Nested
    @DisplayName("createTaskTest()")
    class createTaskTest {
        @Test
        @DisplayName("should throw 404-NOT_FOUND, when no such project")
        public void shouldThrow404WhenNoSuchProject() {
            Long projectId = 10L;
            TaskRequestDTO request = TaskRequestDTO.builder()
                    .taskName("homework")
                    .daysToComplete(10)
                    .build();

            HttpStatus expectedStatus = HttpStatus.NOT_FOUND;

            Mockito
                    .when(projectRepository.findById(projectId))
                    .thenReturn(Optional.empty());


            ResponseStatusException ex = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> service.createTask(request, projectId)
            );
            Assertions.assertEquals(expectedStatus, ex.getStatus());
        }

        @Test
        @DisplayName("should save() task, when there is such project")
        public void shouldSaveTaskWhenThereIsSuchProjectTest() {
            Long projectId = 10L;
            TaskRequestDTO request = TaskRequestDTO.builder()
                    .taskName("homework")
                    .daysToComplete(10)
                    .build();
            Project project = Project.builder()
                    .projectName("CABAS")
                    .build();
            Task task = Task.builder()
                    .taskName(request.getTaskName())
                    .daysToComplete(request.getDaysToComplete())
                    .taskStatus(TaskStatus.TODO)
                    .project(project)
                    .build();
            Mockito
                    .when(projectRepository.findById(projectId))
                    .thenReturn(Optional.of(project));

            Mockito
                    .when(taskRepository.save(
                            ArgumentMatchers.any()))
                    .thenReturn(task);

            service.createTask(request, projectId);
        }

    }

    @Nested
    @DisplayName("pushTaskForward()")
    class pushTaskForward {

        @Test
        @DisplayName("should throw 404-NOT_FOUND, when no such task")
        void pushTaskForwardShouldThrow404WhenNoSuchTaskTest() {
            Long projectId = 1L;
            Long taskId = 10L;

            HttpStatus expectedStatus = HttpStatus.NOT_FOUND;
            Mockito
                    .when(taskRepository.findById(10L))
                    .thenReturn(Optional.empty());

            ResponseStatusException ex = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> service.pushTaskForward(projectId, taskId)
            );
            Assertions.assertEquals(expectedStatus, ex.getStatus());
        }

        @Test
        @DisplayName("should push task forward status todo")
        void pushTaskForwadStatusTodoTest() {
            Long projectId = 1L;
            Long taskId = 10L;
            TaskRequestDTO request = TaskRequestDTO.builder()
                    .taskName("homework")
                    .daysToComplete(10)
                    .build();
            Project project = Project.builder()
                    .projectName("CABAS")
                    .build();
            Task task = Task.builder()
                    .taskName(request.getTaskName())
                    .daysToComplete(request.getDaysToComplete())
                    .taskStatus(TaskStatus.TODO)
                    .project(project)
                    .build();

            Mockito
                    .when(taskRepository.findById(taskId))
                    .thenReturn(Optional.of(task));

            Mockito
                    .when(taskRepository.save(
                            ArgumentMatchers.any()))
                    .thenReturn(task);

            service.pushTaskForward(projectId, taskId);

        }

        @Test
        @DisplayName("should push task forward status in progress")
        void pushTaskForwadStatusInProgressTest() {
            Long projectId = 1L;
            Long taskId = 10L;
            TaskRequestDTO request = TaskRequestDTO.builder()
                    .taskName("homework")
                    .daysToComplete(10)
                    .build();
            Project project = Project.builder()
                    .projectName("CABAS")
                    .build();
            Task task = Task.builder()
                    .taskName(request.getTaskName())
                    .daysToComplete(request.getDaysToComplete())
                    .taskStatus(TaskStatus.IN_PROGRESS)
                    .project(project)
                    .build();

            Mockito
                    .when(taskRepository.findById(taskId))
                    .thenReturn(Optional.of(task));

            Mockito
                    .when(taskRepository.save(
                            ArgumentMatchers.any()))
                    .thenReturn(task);

            service.pushTaskForward(projectId, taskId);

        }
    }

    @Nested
    @DisplayName("pushTaskBackward()")
    class pushTaskBackward {
        @DisplayName("should throw 404-NOT_FOUND, when no such task")
        @Test
        void pushTaskBackwardShouldThrow404WhenNoSuchTaskTest() {
            Long projectId = 1L;
            Long taskId = 10L;


            HttpStatus expectedStatus = HttpStatus.NOT_FOUND;
            Mockito
                    .when(taskRepository.findById(10L))
                    .thenReturn(Optional.empty());


            ResponseStatusException ex = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> service.pushTaskBackward(projectId, taskId)
            );
            Assertions.assertEquals(expectedStatus, ex.getStatus());
        }

        @Test
        @DisplayName("should push task backward status todo")
        void pushTaskBackwardStatusTodoTest() {
            Long projectId = 1L;
            Long taskId = 10L;
            TaskRequestDTO request = TaskRequestDTO.builder()
                    .taskName("homework")
                    .daysToComplete(10)
                    .build();
            Project project = Project.builder()
                    .projectName("CABAS")
                    .build();
            Task task = Task.builder()
                    .taskName(request.getTaskName())
                    .daysToComplete(request.getDaysToComplete())
                    .taskStatus(TaskStatus.TODO)
                    .project(project)
                    .build();

            Mockito
                    .when(taskRepository.findById(taskId))
                    .thenReturn(Optional.of(task));

            Mockito
                    .when(taskRepository.save(
                            ArgumentMatchers.any()))
                    .thenReturn(task);

            service.pushTaskBackward(projectId, taskId);

        }

        @Test
        @DisplayName("should push task backward status in progress")
        void pushTaskBackwardStatusInProgressTest() {
            Long projectId = 1L;
            Long taskId = 10L;
            TaskRequestDTO request = TaskRequestDTO.builder()
                    .taskName("homework")
                    .daysToComplete(10)
                    .build();
            Project project = Project.builder()
                    .projectName("CABAS")
                    .build();
            Task task = Task.builder()
                    .taskName(request.getTaskName())
                    .daysToComplete(request.getDaysToComplete())
                    .taskStatus(TaskStatus.IN_PROGRESS)
                    .project(project)
                    .build();

            Mockito
                    .when(taskRepository.findById(taskId))
                    .thenReturn(Optional.of(task));

            Mockito
                    .when(taskRepository.save(
                            ArgumentMatchers.any()))
                    .thenReturn(task);

            service.pushTaskBackward(projectId, taskId);

        }
    }

    @Nested
    @DisplayName("findByName()")
    class findByName {


        @Test
        @DisplayName("should return ")
        public void shuldFindByName() {
        }
    }

        @Nested
        @DisplayName("findByStatus()")
        class findByStatus {
            @Test
            @DisplayName("should list tasks, when  find by status ")
            void shouldListTasksWhenFindByStatusTest() {
                TaskStatus status = TaskStatus.IN_PROGRESS;
                Long projectId = 1L;
                int expectedResponseSize = 2;
                Project project = Project.builder()
                        .projectName("CABAS")
                        .id(1L)
                        .build();
                List<Task> tasks = of(
                        Task.builder().id(100L).taskName("homework1").daysToComplete(10)
                                .taskStatus(TaskStatus.IN_PROGRESS).project(project)
                                .build(),
                        Task.builder().id(101L).taskName("homework2").daysToComplete(10)
                                .taskStatus(TaskStatus.TODO).project(project)
                                .build(),
                        Task.builder().id(102L).taskName("homework2").daysToComplete(10)
                                .taskStatus(TaskStatus.IN_PROGRESS).project(project)
                                .build(),
                        Task.builder().id(103L).taskName("homework3").daysToComplete(14)
                                .taskStatus(TaskStatus.TODO).project(project)
                                .build()
                );
                Mockito
                        .when(taskRepository.findAllByProjectId(1L))
                        .thenReturn(tasks);

                var response = service.findByStatus(projectId, status);
                Assertions.assertEquals(expectedResponseSize, response.size());

            }

            @Test
            @DisplayName("should list empty, when   no status tasks are found ")
            void shouldListEmptyWhenNoStatusTasksAreFoundTest() {
                TaskStatus status = TaskStatus.TODO;
                Long projectId = 1L;

                Project project = Project.builder()
                        .projectName("CABAS")
                        .id(1L)
                        .build();
                List<Task> tasks = of(
                        Task.builder().id(100L).taskName("homework1").daysToComplete(10)
                                .taskStatus(TaskStatus.IN_PROGRESS).project(project)
                                .build(),
                        Task.builder().id(101L).taskName("homework2").daysToComplete(10)
                                .taskStatus(TaskStatus.IN_PROGRESS).project(project)
                                .build(),
                        Task.builder().id(102L).taskName("homework2").daysToComplete(10)
                                .taskStatus(TaskStatus.IN_PROGRESS).project(project)
                                .build(),
                        Task.builder().id(103L).taskName("homework3").daysToComplete(14)
                                .taskStatus(TaskStatus.IN_PROGRESS).project(project)
                                .build()
                );


                Mockito
                        .when(taskRepository.findAllByProjectId(1L))
                        .thenReturn(tasks);

                var response = service.findByStatus(projectId, status);

                Assertions.assertTrue(response.isEmpty());

            }

        }


        @Nested
        @DisplayName("findAbandonedTasks()")
        class findAbandonedTasks {
            @Test
            @DisplayName("should find abandoned tasks, when  find by status in progress  and for over a week")
            void findAbandonedTasksWhenFindByStatusInProgressAndForOverWeek() {
                Long projectId = 1L;
                int expectedResponseSize = 4;
                LocalDateTime currentTime = LocalDateTime.parse("2022-07-16T08:00:00");
                Project project = Project.builder()
                        .projectName("CABAS")
                        .id(1L)
                        .build();
                List<Task> tasks = of(
                        Task.builder().id(100L).taskName("homework1").daysToComplete(10)
                                .createdOn(LocalDateTime.parse("2022-06-01T08:00:00"))
                                .updatedOn(LocalDateTime.parse("2022-06-10T08:00:00"))
                                .taskStatus(TaskStatus.IN_PROGRESS).project(project)
                                .build(),
                        Task.builder().id(101L).taskName("homework2").daysToComplete(10)
                                .createdOn(LocalDateTime.parse("2022-06-01T08:00:00"))
                                .updatedOn(LocalDateTime.parse("2022-06-11T08:00:00"))
                                .taskStatus(TaskStatus.IN_PROGRESS).project(project)
                                .build(),
                        Task.builder().id(102L).taskName("homework2").daysToComplete(10)
                                .createdOn(LocalDateTime.parse("2022-06-02T08:00:00"))
                                .updatedOn(LocalDateTime.parse("2022-06-12T08:00:00"))
                                .taskStatus(TaskStatus.IN_PROGRESS).project(project)
                                .build(),
                        Task.builder().id(103L).taskName("homework3").daysToComplete(14)
                                .createdOn(LocalDateTime.parse("2022-06-03T08:00:00"))
                                .updatedOn(LocalDateTime.parse("2022-06-13T08:00:00"))
                                .taskStatus(TaskStatus.IN_PROGRESS).project(project)
                                .build()
                );
                Mockito
                        .when(taskRepository.findAllByProjectId(projectId))
                        .thenReturn(tasks);
                var response = service.findAbandonedTasks(projectId);

                Assertions.assertEquals(expectedResponseSize, response.size());


            }


            @Test
            @DisplayName("should list empty, when   no status tasks are found   ")
            void shouldListEmptyWhenNoStatusTasksAreFoundTest() {

                Long projectId = 1L;
                int expectedResponseSize = 0;
                LocalDateTime currentTime = LocalDateTime.parse("2022-07-16T08:00:00");
                Project project = Project.builder()
                        .projectName("CABAS")
                        .id(1L)
                        .build();
                List<Task> tasks = of(
                        Task.builder().id(100L).taskName("homework1").daysToComplete(10)
                                .createdOn(LocalDateTime.parse("2022-06-01T08:00:00"))
                                .updatedOn(LocalDateTime.parse("2022-06-10T08:00:00"))
                                .taskStatus(TaskStatus.TODO).project(project)
                                .build(),
                        Task.builder().id(101L).taskName("homework2").daysToComplete(10)
                                .createdOn(LocalDateTime.parse("2022-06-01T08:00:00"))
                                .updatedOn(LocalDateTime.parse("2022-06-11T08:00:00"))
                                .taskStatus(TaskStatus.TODO).project(project)
                                .build(),
                        Task.builder().id(102L).taskName("homework2").daysToComplete(10)
                                .createdOn(LocalDateTime.parse("2022-06-02T08:00:00"))
                                .updatedOn(LocalDateTime.parse("2022-06-12T08:00:00"))
                                .taskStatus(TaskStatus.TODO).project(project)
                                .build(),
                        Task.builder().id(103L).taskName("homework3").daysToComplete(14)
                                .createdOn(LocalDateTime.parse("2022-06-03T08:00:00"))
                                .updatedOn(LocalDateTime.parse("2022-06-13T08:00:00"))
                                .taskStatus(TaskStatus.TODO).project(project)
                                .build()
                );
                Mockito
                        .when(taskRepository.findAllByProjectId(projectId))
                        .thenReturn(tasks);
                var response = service.findAbandonedTasks(projectId);

                Assertions.assertEquals(expectedResponseSize, response.size());


            }

            @Test
            @DisplayName("should list empty, when   are no found  for over a week ")
            void shouldListEmptyWhenNoeFoundForOverWeekTest() {

                Long projectId = 1L;
                int expectedResponseSize = 0;
                LocalDateTime currentTime = LocalDateTime.parse("2022-07-16T08:00:00");
                Project project = Project.builder()
                        .projectName("CABAS")
                        .id(1L)
                        .build();
                List<Task> tasks = of(
                        Task.builder().id(100L).taskName("homework1").daysToComplete(10)
                                .createdOn(LocalDateTime.parse("2022-06-01T08:00:00"))
                                .updatedOn(LocalDateTime.parse("2022-07-16T08:00:00"))
                                .taskStatus(TaskStatus.IN_PROGRESS).project(project)
                                .build(),
                        Task.builder().id(101L).taskName("homework2").daysToComplete(10)
                                .createdOn(LocalDateTime.parse("2022-06-01T08:00:00"))
                                .updatedOn(LocalDateTime.parse("2022-07-16T08:00:00"))
                                .taskStatus(TaskStatus.IN_PROGRESS).project(project)
                                .build(),
                        Task.builder().id(102L).taskName("homework2").daysToComplete(10)
                                .createdOn(LocalDateTime.parse("2022-06-02T08:00:00"))
                                .updatedOn(LocalDateTime.parse("2022-07-16T08:00:00"))
                                .taskStatus(TaskStatus.IN_PROGRESS).project(project)
                                .build(),
                        Task.builder().id(103L).taskName("homework3").daysToComplete(14)
                                .createdOn(LocalDateTime.parse("2022-06-03T08:00:00"))
                                .updatedOn(LocalDateTime.parse("2022-07-16T08:00:00"))
                                .taskStatus(TaskStatus.IN_PROGRESS).project(project)
                                .build()
                );
                Mockito
                        .when(taskRepository.findAllByProjectId(projectId))
                        .thenReturn(tasks);
                var response = service.findAbandonedTasks(projectId);

                Assertions.assertEquals(expectedResponseSize, response.size());
            }

            @Test
            @DisplayName("should list empty   ")
            void shouldListEmptyTest() {

                Long projectId = 1L;

                LocalDateTime currentTime = LocalDateTime.parse("2022-07-16T08:00:00");
                Project project = Project.builder()
                        .projectName("CABAS")
                        .id(1L)
                        .build();
                List<Task> tasks = of(

                );
                Mockito
                        .when(taskRepository.findAllByProjectId(projectId))
                        .thenReturn(tasks);
                var response = service.findAbandonedTasks(projectId);
                Assertions.assertTrue(response.isEmpty());

            }
        }

    }
