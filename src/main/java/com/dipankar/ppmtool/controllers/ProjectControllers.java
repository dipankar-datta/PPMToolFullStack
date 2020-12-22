package com.dipankar.ppmtool.controllers;

import com.dipankar.ppmtool.domain.Project;
import com.dipankar.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}