package com.gui.projectmanagementserver.entity;

public class ProjectPreview {
    private String client_role ;
    private String id ;
    private String name ;
    private String start_date ;
    private String end_date ;
    private String status ;
    private String budget ;
    private ClientPreview creator ;
    private ClientPreview manager ;
    private int num_members ;
    private int number_tasks ;

    public ProjectPreview(String client_role, String id, String name, String start_date, String end_date, String status, String budget, ClientPreview creator, ClientPreview manager, int num_members, int number_tasks) {
        this.client_role = client_role;
        this.id = id;
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
        this.budget = budget;
        this.creator = creator;
        this.manager = manager;
        this.num_members = num_members;
        this.number_tasks = number_tasks;
    }

    public String getClient_role() {
        return client_role;
    }

    public void setClient_role(String client_role) {
        this.client_role = client_role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public ClientPreview getCreator() {
        return creator;
    }

    public void setCreator(ClientPreview creator) {
        this.creator = creator;
    }

    public ClientPreview getManager() {
        return manager;
    }

    public void setManager(ClientPreview manager) {
        this.manager = manager;
    }

    public int getNum_members() {
        return num_members;
    }

    public void setNum_members(int num_members) {
        this.num_members = num_members;
    }

    public int getNumber_tasks() {
        return number_tasks;
    }

    public void setNumber_tasks(int number_tasks) {
        this.number_tasks = number_tasks;
    }
}
