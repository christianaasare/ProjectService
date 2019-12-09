package io.turnatabl.ProjectService.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.turnatabl.ProjectService.DAOs.ProjectDAO;
import io.turnatabl.ProjectService.models.Developer;
import io.turnatabl.ProjectService.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
public class ProjectController implements ProjectDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @ApiOperation("Get a list of all projects")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/projects")
    @Override
    public List<Project> getAllProjects() {
        return this.jdbcTemplate.query("select * from projects", BeanPropertyRowMapper.newInstance(Project.class));

    }

    @ApiOperation("Get a list of uncompleted projects")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/projects/available")
    @Override
    public List<Project> getAllAvailableProjects() {
        return null;
    }


    @ApiOperation("Get a project by Id")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/projects/{id}")
    @Override
    public Project getProjectById(Integer project_id) {
        return null;
    }

    @ApiOperation("Remove a project by Id")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/projects/{id}")
    @Override
    public void deleteProjectById(Integer project_id) {

    }

    @ApiOperation("Update a project by Id")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/projects/{id}")
    @Override
    public void updateProject(Project project) {

    }

    @ApiOperation("Add a project")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/projects/{id}")
    @Override
    public void addProject(Project project) {

    }

    @ApiOperation("Get completed projects")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/projects/completed")
    @Override
    public List<Project> getCompletedProject() {
        return null;
    }
    @ApiOperation("Search for projects")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/projects/search/{projectName}")
    @Override
    public List<Project> searchProject(String project_name) {
        return null;
    }

    @ApiOperation("Assign project to a list of developers")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/projects/assign")
    @Override
    public void assignProject(List<Integer> developerIds) {

    }


}
