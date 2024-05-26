package com.gui.projectmanagementserver.entity;

public class MessageSend {
    private String sender_id ;
    private String receiver_id ;
    private String type_message ;
    private String message ;
    private ImageSend image ;
    private FileSend file ;

    public MessageSend(String sender_id, String receiver_id, String type_message, String message, ImageSend image, FileSend file) {
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.type_message = type_message;
        this.message = message;
        this.image = image;
        this.file = file;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(String receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getType_message() {
        return type_message;
    }

    public void setType_message(String type_message) {
        this.type_message = type_message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ImageSend getImage() {
        return image;
    }

    public void setImage(ImageSend image) {
        this.image = image;
    }

    public FileSend getFile() {
        return file;
    }

    public void setFile_id(FileSend file_id) {
        this.file = file_id;
    }
}
