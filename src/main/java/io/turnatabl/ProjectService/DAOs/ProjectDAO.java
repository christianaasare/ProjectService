package io.turnatabl.ProjectService.DAOs;

import io.turnatabl.ProjectService.models.Developer;
import io.turnatabl.ProjectService.models.Project;

import java.util.List;

public interface ProjectDAO {
    List<Project> getAllProjects();

    List<Project> getAllAvailableProjects();

    Project getProjectById(Integer project_id);

    void deleteProjectById(Integer project_id);

    void updateProject(Project project,Integer project_id);

    void addProject(Project project);

    List<Project> getCompletedProject();

    List<Project> searchProject(String project_name);

    void assignProject(Integer developersId,Integer project_id);

    Project getCurrentProjectByDevId(Integer emp_id);

    Project getTaskByID(Integer emp_id);


}
