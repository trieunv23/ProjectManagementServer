package com.gui.projectmanagementserver.entity;

public class CreateFeedBack {
    private String feedback_sender ;
    private String project_id ;
    private String task_id ;
    private String product_id ;
    private String feedback ;

    public CreateFeedBack(String feedback_sender, String project_id, String task_id, String product_id, String feedback) {
        this.feedback_sender = feedback_sender;
        this.project_id = project_id;
        this.task_id = task_id;
        this.product_id = product_id;
        this.feedback = feedback;
    }

    public String getFeedback_sender() {
        return feedback_sender;
    }

    public void setFeedback_sender(String feedback_sender) {
        this.feedback_sender = feedback_sender;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
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

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
