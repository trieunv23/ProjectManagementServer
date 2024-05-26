package com.gui.projectmanagementserver.entity;

public class TaskObject {
    private String task_id ;
    private String classify ;
    private String project_id ;
    private String container_id ;
    private String task_name ;
    private String job_requirements ;
    private String undertaker ;
    private String request_date ;
    private String deadline ;
    private String creator ;
    private String product_id ;

    public TaskObject(String task_id, String classify, String project_id, String container_id, String task_name, String job_requirements, String undertaker, String request_date, String deadline, String creator, String product_id) {
        this.task_id = task_id;
        this.classify = classify;
        this.project_id = project_id;
        this.container_id = container_id;
        this.task_name = task_name;
        this.job_requirements = job_requirements;
        this.undertaker = undertaker;
        this.request_date = request_date;
        this.deadline = deadline;
        this.creator = creator;
        this.product_id = product_id;
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

    public String getJob_requirements() {
        return job_requirements;
    }

    public void setJob_requirements(String job_requirements) {
        this.job_requirements = job_requirements;
    }

    public String getUndertaker() {
        return undertaker;
    }

    public void setUndertaker(String undertaker) {
        this.undertaker = undertaker;
    }

    public String getRequest_date() {
        return request_date;
    }

    public void setRequest_date(String request_date) {
        this.request_date = request_date;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

}
