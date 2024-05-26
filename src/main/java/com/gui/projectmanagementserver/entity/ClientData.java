package com.gui.projectmanagementserver.entity;

public class ClientData {
    private String id ;
    private byte[] avata ;
    private String fullname ;
    private String day_of_birth ;
    private String gender ;
    private String email ;
    private String phonenumber ;

    public ClientData(String id, byte[] avata, String fullname, String day_of_birth, String gender, String email, String phonenumber) {
        this.id = id;
        this.avata = avata;
        this.fullname = fullname;
        this.day_of_birth = day_of_birth;
        this.gender = gender;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getAvata() {
        return avata;
    }

    public void setAvata(byte[] avata) {
        this.avata = avata;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

}
