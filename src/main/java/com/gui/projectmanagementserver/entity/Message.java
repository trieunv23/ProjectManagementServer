package com.gui.projectmanagementserver.entity;

import java.io.File;

public class Message {
    private String message_id ;
    private String sender_id ;
    private String receiver_id ;
    private String type_message ;
    private String message ;
    private ImageObject image ;
    private FileObject file_id ;
    private String day_send ;

    public Message(String message_id, String sender_id, String receiver_id, String type_message, String message, ImageObject image, FileObject file_id, String day_send) {
        this.message_id = message_id;
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.type_message = type_message;
        this.message = message;
        this.image = image;
        this.file_id = file_id;
        this.day_send = day_send;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
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

    public ImageObject getImage() {
        return image;
    }

    public void setImage(ImageObject image) {
        this.image = image;
    }

    public FileObject getFile_id() {
        return file_id;
    }

    public void setFile_id(FileObject file_id) {
        this.file_id = file_id;
    }

    public String getDay_send() {
        return day_send;
    }

    public void setDay_send(String day_send) {
        this.day_send = day_send;
    }
}
