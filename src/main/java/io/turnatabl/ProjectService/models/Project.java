package io.turnatabl.ProjectService.models;

import java.util.Date;

public class Project {
    private int project_id;
    private String title;



    public Project() {
    }


    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "Project{" +
                "project_id=" + project_id +
                ", title='" + title + '\'' +

                '}';
    }


}
