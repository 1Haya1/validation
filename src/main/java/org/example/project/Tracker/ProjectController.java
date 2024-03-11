package org.example.project.Tracker;

import jakarta.validation.Valid;
import org.example.project.Api.ApiResponse;
import org.example.project.Model.Project;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final List<Project> projects =new ArrayList<>();
    private long projectId=1;



  @PostMapping("/add")
  public ResponseEntity addProject(@RequestBody @Valid Project project){
      project.setId(projectId++);
      projects.add(project);
      return ResponseEntity.status(200).body(new ApiResponse("project added"));
  }

@GetMapping("/all")
    public ResponseEntity <List<Project>> getAllProjects(){
      return ResponseEntity.status(200).body(projects);
}

@PutMapping("/change/status/{id}")
public ResponseEntity changeProjectsStatus(@PathVariable long id){
      for(Project project : projects){
          if(project.getId() == id){
              project.setStatus(!project.isStatus());
              return ResponseEntity.status(200).body(new ApiResponse("projects changed"));
          }
      }
      return ResponseEntity.status(400).body(new ApiResponse("project not found"));
}




    @PutMapping("/update/{id}")
    public ResponseEntity updateProject(@PathVariable("id") long id, @RequestBody Project updatedProject) {
        for (Project project : projects) {
            if (project.getId() == id) {
                project.setTitle(updatedProject.getTitle());
                project.setDescription(updatedProject.getDescription());
                project.setStatus(updatedProject.isStatus());
                project.setCompanyName(updatedProject.getCompanyName());
                return ResponseEntity.status(200).body(new ApiResponse("project updated"));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("project not found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProject(@PathVariable long id) {
        try {
            projects.removeIf(project -> project.getId() == id);
              return ResponseEntity.status(200).body(new ApiResponse("deleted done"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ApiResponse("error in delete"));
        }
    }


    @GetMapping("/search/{title}")
    public ResponseEntity<List<Project>> searchByTitle(@PathVariable String title) {
        List<Project> foundProjects = new ArrayList<>();
        for (Project project : projects) {
            if (project.getTitle().equals(title)) {
                foundProjects.add(project);
            }
        }
        return ResponseEntity.ok(foundProjects);
    }


    @GetMapping("/company/{companyName}")
    public ResponseEntity <List<Project>> getAllProjectsByCompanyName(@PathVariable String companyName) {
        List<Project> projectsByCompany = new ArrayList<>();
        for (Project project : projects) {
            if (project.getCompanyName().equalsIgnoreCase(companyName)) {
                projectsByCompany.add(project);
            }
        }
        return ResponseEntity.ok(projectsByCompany);
    }
















}
