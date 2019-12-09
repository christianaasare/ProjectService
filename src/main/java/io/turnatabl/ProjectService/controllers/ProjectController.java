package io.turnatabl.ProjectService.controllers;

import io.turnatabl.ProjectService.DAOs.ProjectDAO;
import io.turnatabl.ProjectService.models.Project;

import java.util.List;

public class ProjectController implements ProjectDAO {

    @Override
    public List<Project> getAllProjects() {
        return null;
    }

    @Override
    public List<Project> getAllAvailableProjects() {
        return null;
    }

    @Override
    public Project getProjectById(Integer project_id) {
        return null;
    }

    @Override
    public void deleteProjectById(Integer project_id) {

    }

    @Override
    public void updateProject(Project project) {

    }

    @Override
    public void addProject(Project project) {

    }

    @Override
    public List<Project> getCompletedProject() {
        return null;
    }

    @Override
    public List<Project> searchProject() {
        return null;
    }
}
