package de.telran.management_system.entity;

import de.telran.management_system.entity.types.TaskStatus;
import de.telran.management_system.entity.types.TaskStatusConverter;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;




import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_name", unique = true)
    private String taskName;

    @Column(name = "days_to_complete")
    private Integer daysToComplete;

    @Column(name = "task_status")
    @Convert(converter = TaskStatusConverter.class)
    private TaskStatus taskStatus;

    @Column(name = "created_on")
    @CreatedDate
    private LocalDateTime createdOn;

    @Column(name = "updated_on")
    @LastModifiedDate
    private LocalDateTime updatedOn;

    @JoinColumn
    @ManyToOne
    private Project project;
}