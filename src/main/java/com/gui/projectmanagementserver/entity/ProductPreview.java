package com.gui.projectmanagementserver.entity;

public class ProductPreview {
    private String product_id ;
    private String product_name ;
    private String file_name ;
    private int file_size ;
    private String finish_day ;
    private String creator ;

    public ProductPreview(String product_id, String product_name, String file_name, int file_size, String finish_day, String creator) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.file_name = file_name;
        this.file_size = file_size;
        this.finish_day = finish_day;
        this.creator = creator;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
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

    public String getFinish_day() {
        return finish_day;
    }

    public void setFinish_day(String finish_day) {
        this.finish_day = finish_day;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
