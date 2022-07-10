package de.telran.management_system.dto;


import de.telran.management_system.entity.types.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskResponseDTO {

    private Long id;
    private String taskName;
    private Integer daysToComplete;
    private TaskStatus taskStatus;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Long projectId;
}