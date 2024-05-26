package com.gui.projectmanagementserver.entity;

public class CreateProjectObject {
    private String name_project ;
    private String describe ;
    private String expected_end ;
    private String budget ;
    private String creator ;

    public CreateProjectObject(String name_project, String describe, String expected_end, String budget, String creator) {
        this.name_project = name_project;
        this.describe = describe;
        this.expected_end = expected_end;
        this.budget = budget;
        this.creator = creator;
    }

    public String getName_project() {
        return name_project;
    }

    public void setName_project(String name_project) {
        this.name_project = name_project;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getExpected_end() {
        return expected_end;
    }

    public void setExpected_end(String expected_end) {
        this.expected_end = expected_end;
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
}
