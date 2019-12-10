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
//        return this.jdbcTemplate.query("select project.title from projects inner join current_projects on projects.project_id = current_project.project_id where current_project");
        return this.jdbcTemplate.query("select * from projects where completed = 0", BeanPropertyRowMapper.newInstance(Project.class));
    }


    @ApiOperation("Get a project by Id")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/projects/{id}")
    @Override
    public Project getProjectById(@PathVariable Integer project_id) {
        return (Project) this.jdbcTemplate.query("select * from projects where project_id = 1",
                new Object[]{project_id},
                BeanPropertyRowMapper.newInstance(Project.class));
    }

    @ApiOperation("Remove a project by Id")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/projects/{id}")
    @Override
    public void deleteProjectById(@PathVariable Integer project_id) {
        this.jdbcTemplate.update("delete from customers where customer_id = ?", project_id);
    }

    @ApiOperation("Update a project by Id")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/projects/{id}")
    @Override
    public void updateProject(@RequestBody Project project, @PathVariable Integer project_id ) {
        this.jdbcTemplate.update("update projects set title = ?, description = ?, where project_id = ?", project.getTitle(), project.getDescription(), project_id);
    }

    @ApiOperation("Add a project")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/projects")
    @Override
    public void addProject(@RequestBody Project project) {
       this.jdbcTemplate.update("insert into projects (title, description ) values (?,?)", project.getTitle(), project.getDescription());
    }

    @ApiOperation("Get completed projects")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/projects/completed")
    @Override
    public List<Project> getCompletedProject() {
        return this.jdbcTemplate.query("select * from projects where completed = 1", BeanPropertyRowMapper.newInstance(Project.class));
    }
    @ApiOperation("Search for projects by title")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/projects/search/{project_title}")
    @Override
    public List<Project> searchProject(@PathVariable String project_title) {
        return this.jdbcTemplate.query("select * from projects where title like ?",
                new Object[]{project_title + "%"},
                BeanPropertyRowMapper.newInstance(Project.class));
    }

    @ApiOperation("Assign project to a list of developers")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/projects/assign/{project_id}")
    @Override
    public void assignProject(@RequestBody List<Integer> developerIds, @PathVariable Integer project_id) {
//        developerIds.stream().forEach(id -> System.out.println(id));
        developerIds.stream().forEach(ids ->
                this.jdbcTemplate.update(
                        "insert into currentprojects (developer_id, project_id) values (?,?)", ids, project_id));

    }


}
