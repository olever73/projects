package de.telran.management_system.controller;


import de.telran.management_system.dto.ProjectRequestDTO;
import de.telran.management_system.dto.ProjectResponseDTO;
import de.telran.management_system.servise.ProjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@AllArgsConstructor
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/project")
    public void createProject(@RequestBody @Valid ProjectRequestDTO request) {
        projectService.createProject(request);
    }

    @GetMapping("/project/{id}/task")
    public ProjectResponseDTO findAll(@PathVariable("id") Long id,
                                      @RequestParam int pageSize,
                                      @RequestParam int pageNumber) {
        return projectService.findAll(id, pageSize, pageNumber);
    }


}