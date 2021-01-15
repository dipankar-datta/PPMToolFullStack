package com.dipankar.ppmtool.services;

import com.dipankar.ppmtool.domain.Project;
import com.dipankar.ppmtool.exceptions.ProjectIdException;
import com.dipankar.ppmtool.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveOrUpdate(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID "
                    + project.getProjectIdentifier().toUpperCase() + " already exists");
        }
    }

    public Project findByIdentifier(String identifier) {

        Project project = projectRepository.findByProjectIdentifier(identifier);

        if (project == null) {
            throw new ProjectIdException("Project doesn't exist");
        }
        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null) {
            throw new ProjectIdException("Cannot delete project with ID " + projectId + ". This project doesn't exist");
        }

        projectRepository.delete(project);
    }
}
