package com.gui.projectmanagementserver.entity;

public class FileObject {
    private String file_id ;
    private String name_file ;
    private byte[] file_data ;
    private int file_size ;

    public FileObject(String file_id, String name_file, byte[] file_data, int file_size) {
        this.file_id = file_id;
        this.name_file = name_file;
        this.file_data = file_data;
        this.file_size = file_size;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getName_file() {
        return name_file;
    }

    public void setName_file(String name_file) {
        this.name_file = name_file;
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
