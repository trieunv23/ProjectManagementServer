package com.gui.projectmanagementserver.entity;

import java.util.List;

public class ProjectControl {
    private ProjectPreview pp ;
    private List<TaskPreview> tasks ;
    private List<ClientObject> members ;

    public ProjectControl(ProjectPreview pp, List<TaskPreview> tasks, List<ClientObject> members) {
        this.pp = pp;
        this.tasks = tasks;
        this.members = members;
    }

    public ProjectPreview getPp() {
        return pp;
    }

    public void setPp(ProjectPreview pp) {
        this.pp = pp;
    }

    public List<TaskPreview> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskPreview> tasks) {
        this.tasks = tasks;
    }

    public List<ClientObject> getMembers() {
        return members;
    }

    public void setMembers(List<ClientObject> members) {
        this.members = members;
    }
}
