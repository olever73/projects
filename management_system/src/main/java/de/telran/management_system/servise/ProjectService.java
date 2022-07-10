package de.telran.management_system.servise;

import de.telran.management_system.dto.ProjectRequestDTO;
import de.telran.management_system.dto.ProjectResponseDTO;

public interface ProjectService {

    void createProject(ProjectRequestDTO request);

    ProjectResponseDTO findAll(Long Id);




}