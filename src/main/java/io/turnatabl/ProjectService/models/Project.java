package io.turnatabl.ProjectService.models;

import java.util.Date;

public class Project {
    private int project_id;
    private String title;

    @Override
    public String toString() {
        return "Project{" +
                "project_id=" + project_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", devno=" + devno +
                '}';
    }

    private String description;
    private Date startdate;
    private Date enddate;
    private int devno;

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public int getDevno() {
        return devno;
    }

    public void setDevno(int devno) {
        this.devno = devno;
    }



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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
