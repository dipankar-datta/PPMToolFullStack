package com.dipankar.ppmtool.controllers;

import com.dipankar.ppmtool.domain.Project;
import com.dipankar.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.dipankar.ppmtool.util.ValidationErrorUtil.validateError;

@RestController
@RequestMapping("/api/project")
public class ProjectControllers {

    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
        return result.hasErrors() ?
                validateError(result)
                : new ResponseEntity(projectService.saveOrUpdate(project), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{identifier}")
    public ResponseEntity<?> getProjectById(@PathVariable String identifier) {

        return new ResponseEntity<>(projectService.findByIdentifier(identifier), HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects() {
        return projectService.findAllProjects();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId) {
        projectService.deleteProjectByIdentifier(projectId);
        return new ResponseEntity("Project with ID " + projectId + "successfully deleted", HttpStatus.OK);
    }
}
