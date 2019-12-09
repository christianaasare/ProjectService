package io.turnatabl.ProjectService.DAOs;

import io.turnatabl.ProjectService.models.Developer;
import io.turnatabl.ProjectService.models.Project;

import java.util.List;

public interface ProjectDAO {
    List<Project> getAllProjects();

    List<Project> getAllAvailableProjects();

    Project getProjectById(Integer project_id);

    void deleteProjectById(Integer project_id);

    void updateProject(Project project);

    void addProject(Project project);

    List<Project> getCompletedProject();

    List<Project> searchProject(String project_name);

    void assignProject(List<Integer> developerIds);


}
