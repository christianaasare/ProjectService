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
        return this.jdbcTemplate.query("select * from projects where completed = 0", BeanPropertyRowMapper.newInstance(Project.class));
    }


    @ApiOperation("Get a project by Id")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/projects/{project_id}")
    @Override
    public Project getProjectById(@PathVariable("project_id") Integer project_id) {

        List<Project>  projects = jdbcTemplate.query("select * from projects where project_id = ?",
                new Object[]{project_id},
                BeanPropertyRowMapper.newInstance(Project.class));
        return projects.get(0);
    }

    @ApiOperation("Remove a project by Id")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Override
    @DeleteMapping("/projects/{project_id}")
    public void deleteProjectById(@PathVariable("project_id") Integer project_id) {
        jdbcTemplate.update("delete from projects where project_id = ?", project_id);
    }

    @ApiOperation("Update a project by Id")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Override
    @PutMapping("/projects/{project_id}")
    public void updateProject(@RequestBody Project project, @PathVariable Integer project_id ) {
        jdbcTemplate.update("update projects set title = ? where project_id = ?", project.getTitle(), project_id);
    }

    @ApiOperation("Add a project")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/projects")
    @Override
    public void addProject(@RequestBody Project project) {
       this.jdbcTemplate.update("insert into projects (title) values (?)", project.getTitle());
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
    public void assignProject(@RequestBody List<Integer> emp_ids, @PathVariable Integer project_id) {

        emp_ids.stream().forEach(ids ->
                this.jdbcTemplate.update(
                        "insert into currentprojects (emp_id, project_id) values (?,?)", ids, project_id));

    }

    @ApiOperation("Get a assigned projects for a Developer")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/currentproject/dev/{emp_id}")
    @Override
    public Project getCurrentProjectByDevId(@PathVariable("emp_id") Integer emp_id) {

        List<Project>  projects = jdbcTemplate.query("SELECT projects.title, projects.project_id FROM projects " +
                        "INNER JOIN currentprojects ON currentprojects.project_id = projects.project_id " +
                        "INNER JOIN employees ON currentprojects.emp_id = employees.emp_id where employees.emp_id = ?",
                new Object[]{emp_id},
                BeanPropertyRowMapper.newInstance(Project.class));
        return projects.get(0);
    }

}
