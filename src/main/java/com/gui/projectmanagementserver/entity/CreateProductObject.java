package com.gui.projectmanagementserver.entity;

public class CreateProductObject {
    private String task_id ;
    private String product_name ;
    private byte[] file_data ;
    private String file_name ;
    private int file_size ;
    private String creator ;

    public CreateProductObject(String product_name, byte[] file_data, String file_name, int file_size, String creator, String task_id) {
        this.product_name = product_name;
        this.file_data = file_data;
        this.file_name = file_name;
        this.file_size = file_size;
        this.creator = creator;
        this.task_id = task_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public byte[] getFile_data() {
        return file_data;
    }

    public void setFile_data(byte[] file_data) {
        this.file_data = file_data;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public int getFile_size() {
        return file_size;
    }

    public void setFile_size(int file_size) {
        this.file_size = file_size;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }
}
