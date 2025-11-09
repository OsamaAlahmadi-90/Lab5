package com.example.trackersystem.Controller;

import com.example.trackersystem.Api.ApiResponse;
import com.example.trackersystem.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    ArrayList<Project> projects = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Project> getProjects(){
        return projects;
    }

    @PostMapping("/add")
    public ApiResponse addProject(@RequestBody Project project){
        projects.add(project);
        return new ApiResponse ("Project: "+ project.getTitle() + " has been added.");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateProject(@PathVariable int index, @RequestBody Project project){
        projects.set(index, project);
        return new ApiResponse("Project has been updated");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteProject(@PathVariable int index){
        projects.remove(index);
        return new ApiResponse("Project has been deleted");
    }

    @PutMapping("/change-status/{id}")
    public ApiResponse changeStatus(@PathVariable String id){

        for (Project p : projects){
            if(p.getId().equals(id) ){

                if(p.getStatus().equalsIgnoreCase("not done")){
                   p.setStatus("Done");
               } else {
                   p.setStatus("Not done");
               }
                return new ApiResponse("Project " +p.getTitle() +" Status has been changed");
            }
        }

        return new ApiResponse("Couldn't find project with id: " + id );
    }

    @GetMapping("/serach-by-name/{title}")
    public Project SearchProjectByName(@PathVariable String title){
        for(Project p: projects){
           if(p.getTitle().equalsIgnoreCase(title)){
               return p;
           }
        }
        return null;
    }

    @GetMapping("/get-by-company/{companyName}")
    public ArrayList<Project> displayProjectsByCompanyName(@PathVariable String companyName){
        ArrayList<Project> projectsByCompanyName = new ArrayList<>();

        for (Project p : projects){
            if(p.getCompanyName().equalsIgnoreCase(companyName)){
                projectsByCompanyName.add(p);
            }
        }

        return projectsByCompanyName;
    }


}
