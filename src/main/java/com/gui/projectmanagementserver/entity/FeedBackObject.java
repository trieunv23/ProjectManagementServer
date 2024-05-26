package com.gui.projectmanagementserver.entity;

public class FeedBackObject {
    private String feedback_id ;
    private ContactObject sender_feedback ;
    private String projec_id ;
    private String task_id ;
    private String product_id ;
    private String product_creator ;
    private String project_name ;
    private String task_name ;
    private String feedback;
    private String feedback_date ;

    public FeedBackObject(String feedback_id, ContactObject sender_feedback, String projec_id, String task_id, String product_id, String product_creator, String project_name, String task_name, String feedback, String feedback_date) {
        this.feedback_id = feedback_id;
        this.sender_feedback = sender_feedback;
        this.projec_id = projec_id;
        this.task_id = task_id;
        this.product_id = product_id;
        this.product_creator = product_creator;
        this.project_name = project_name;
        this.task_name = task_name;
        this.feedback = feedback;
        this.feedback_date = feedback_date;
    }

    public String getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(String feedback_id) {
        this.feedback_id = feedback_id;
    }

    public ContactObject getSender_feedback() {
        return sender_feedback;
    }

    public void setSender_feedback(ContactObject sender_feedback) {
        this.sender_feedback = sender_feedback;
    }

    public String getProjec_id() {
        return projec_id;
    }

    public void setProjec_id(String projec_id) {
        this.projec_id = projec_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_creator() {
        return product_creator;
    }

    public void setProduct_creator(String product_creator) {
        this.product_creator = product_creator;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getFeedback_date() {
        return feedback_date;
    }

    public void setFeedback_date(String feedback_date) {
        this.feedback_date = feedback_date;
    }
}
