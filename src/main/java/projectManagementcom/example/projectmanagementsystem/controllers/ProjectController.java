package projectManagementcom.example.projectmanagementsystem.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import projectManagementcom.example.projectmanagementsystem.exceptions.ProjectAlreadyExistException;
import projectManagementcom.example.projectmanagementsystem.exceptions.ProjectNotFoundException;
import projectManagementcom.example.projectmanagementsystem.models.Project;
import projectManagementcom.example.projectmanagementsystem.services.ProjectServices;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectServices projectServices;

//    http://localhost:8085/api/projects/add
@PostMapping("/add")
public ResponseEntity<?> addProject(@Valid @RequestBody Project project, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
        return ResponseEntity.badRequest().body("Validation errors");
    }

    try {
        Project addedProject = projectServices.addProject(project);
        return ResponseEntity.ok().body(addedProject);
    } catch (ProjectAlreadyExistException e) {
        return ResponseEntity.status(409).body("Project already exists");
    }
}

//    http://localhost:8085/api/projects/delete/1
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable int id) {
        try {
            if (projectServices.deleteProject(id)) {
                return ResponseEntity.ok().body("Project deleted successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (ProjectNotFoundException e) {
            return ResponseEntity.status(404).body("Project not found");
        }
    }
//    http://localhost:8085/api/projects/update/1
@PutMapping("/update/{id}")
public ResponseEntity<?> updateProject(
        @Valid @RequestBody Project project,
        @PathVariable int id,
        BindingResult bindingResult
) {
    if (bindingResult.hasErrors()) {
        return ResponseEntity.badRequest().body("Validation errors");
    }
    try {
        Project updatedProject = projectServices.updateProject(project, id);

        if (updatedProject != null) {
            return ResponseEntity.ok().body(updatedProject);
        } else {
            return ResponseEntity.status(404).body("Project not found!");
        }
    } catch (ProjectNotFoundException e) {
        return ResponseEntity.status(404).body("Project not found!");

    }
}
//    http://localhost:8085/api/projects/all
    @GetMapping("/all")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projectList = projectServices.getAllProjects();
        return ResponseEntity.ok().body(projectList);
    }

//    http://localhost:8085/api/projects/1
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable int id) {
        Optional<Project> projectOptional = projectServices.getEntityById(id);
        return projectOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

