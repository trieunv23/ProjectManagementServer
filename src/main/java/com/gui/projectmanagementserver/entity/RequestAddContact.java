package com.gui.projectmanagementserver.entity;

public class RequestAddContact {
    private ContactObject contact_send_request ;
    private String request_id ;
    private String request_date ;

    public RequestAddContact(ContactObject contact_send_request, String request_id, String request_date) {
        this.contact_send_request = contact_send_request;
        this.request_id = request_id;
        this.request_date = request_date;
    }

    public ContactObject getContact_send_request() {
        return contact_send_request;
    }

    public void setContact_send_request(ContactObject contact_send_request) {
        this.contact_send_request = contact_send_request;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getRequest_date() {
        return request_date;
    }

    public void setRequest_date(String request_date) {
        this.request_date = request_date;
    }
}
