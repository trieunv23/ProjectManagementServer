package com.gui.projectmanagementserver.entity;

public class FileSend {
    private String file_name ;
    private byte[] file_data ;
    private int file_size ;

    public FileSend(String file_name, byte[] file_data, int file_size) {
        this.file_name = file_name;
        this.file_data = file_data;
        this.file_size = file_size;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public byte[] getFile_data() {
        return file_data;
    }

    public void setFile_data(byte[] file_data) {
        this.file_data = file_data;
    }

    public int getFile_size() {
        return file_size;
    }

    public void setFile_size(int file_size) {
        this.file_size = file_size;
    }

}
