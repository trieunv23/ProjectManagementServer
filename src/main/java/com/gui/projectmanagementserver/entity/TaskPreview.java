package com.gui.projectmanagementserver.entity;

public class TaskPreview {
    private String task_id ;
    private String classify ;
    private String project_id ;
    private String container_id ;
    private String task_name ;

    public TaskPreview(String task_id, String classify, String project_id, String container_id, String task_name) {
        this.task_id = task_id;
        this.classify = classify;
        this.project_id = project_id;
        this.container_id = container_id;
        this.task_name = task_name;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getContainer_id() {
        return container_id;
    }

    public void setContainer_id(String container_id) {
        this.container_id = container_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }
}
