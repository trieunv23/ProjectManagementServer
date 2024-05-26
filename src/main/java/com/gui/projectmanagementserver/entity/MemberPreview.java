package com.gui.projectmanagementserver.entity;

public class MemberPreview {
    private String member_id ;
    private String fullname ;
    private String email ;
    private String phonenumber ;
    private byte[] avata ;
    private String role ;
    private String project_id ;

    public MemberPreview(String member_id, String fullname, String email, String phonenumber, byte[] avata, String role, String project_id) {
        this.member_id = member_id;
        this.fullname = fullname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.avata = avata;
        this.role = role;
        this.project_id = project_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public byte[] getAvata() {
        return avata;
    }

    public void setAvata(byte[] avata) {
        this.avata = avata;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }
}
