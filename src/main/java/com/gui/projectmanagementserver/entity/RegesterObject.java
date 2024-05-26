package com.gui.projectmanagementserver.entity;

import javafx.scene.control.DatePicker;

public class RegesterObject {
    private String f_name ;
    private String l_name ;
    private String day_of_birth ;
    private String gender ;
    private String email ;
    private String phonenumber ;
    private String username ;
    private String passwowrd ;

    public RegesterObject(String f_name, String l_name, String day_of_birth, String gender, String email, String phonenumber, String username, String passwowrd) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.day_of_birth = day_of_birth;
        this.gender = gender;
        this.email = email;
        this.phonenumber = phonenumber;
        this.username = username;
        this.passwowrd = passwowrd;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getDay_of_birth() {
        return day_of_birth;
    }

    public void setDay_of_birth(String day_of_birth) {
        this.day_of_birth = day_of_birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswowrd() {
        return passwowrd;
    }

    public void setPasswowrd(String passwowrd) {
        this.passwowrd = passwowrd;
    }
}
