package com.gui.projectmanagementserver.entity;

public class ClientTemp {
    private String client_id ;
    private String username ;
    private String email ;

    public ClientTemp(String client_id, String username, String email) {
        this.client_id = client_id;
        this.username = username;
        this.email = email;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
