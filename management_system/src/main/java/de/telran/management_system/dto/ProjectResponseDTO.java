package de.telran.management_system.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProjectResponseDTO {

    private Long id;
    private String projectName;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private List<TaskResponseDTO> tasks;
}
