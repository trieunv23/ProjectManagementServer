package com.gui.projectmanagementserver.entity;

import java.util.List;

public class ProjectObject {
    private String id ;
    private String name ;
    private String description ;
    private String start_date ;
    private String end_date ;
    private String status ;
    private String budget ;
    private String creator ;
    private String manager ;
    private List<MemberObject> member ;

    public ProjectObject(String id, String name, String description, String start_date, String end_date, String status, String budget, String creator, String manager, List<MemberObject> member) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
        this.budget = budget;
        this.creator = creator;
        this.manager = manager;
        this.member = member;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public List<MemberObject> getMember() {
        return member;
    }

    public void setMember(List<MemberObject> member) {
        this.member = member;
    }
}
