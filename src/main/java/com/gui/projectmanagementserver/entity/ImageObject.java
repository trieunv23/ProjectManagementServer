package com.gui.projectmanagementserver.entity;

public class ImageObject {
    private String image_id ;
    private byte[] image_data ;
    private int image_size ;

    public ImageObject(String image_id, byte[] image_data, int image_size) {
        this.image_id = image_id;
        this.image_data = image_data;
        this.image_size = image_size;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public byte[] getImage_data() {
        return image_data;
    }

    public void setImage_data(byte[] image_data) {
        this.image_data = image_data;
    }

    public int getImage_size() {
        return image_size;
    }

    public void setImage_size(int image_size) {
        this.image_size = image_size;
    }
}
