package com.gui.projectmanagementserver.entity;

public class ImageSend {
    private byte[] image_data ;
    private int image_size ;

    public ImageSend(byte[] image_data, int image_size) {
        this.image_data = image_data;
        this.image_size = image_size;
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
