package com.gui.projectmanagementserver.entity;

import java.util.Arrays;
import java.util.Objects;

public class BasicInformation {
    private String client_id ;
    private String fullname ;
    private byte[] avata ;
    private String day_of_birth ;
    private String gender ;

    public BasicInformation(String client_id, String fullname, byte[] avata, String day_of_birth, String gender) {
        this.client_id = client_id;
        this.fullname = fullname;
        this.avata = avata;
        this.day_of_birth = day_of_birth;
        this.gender = gender;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public byte[] getAvata() {
        return avata;
    }

    public void setAvata(byte[] avata) {
        this.avata = avata;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BasicInformation other = (BasicInformation) obj;
        return Objects.equals(client_id, other.client_id) &&
                Objects.equals(fullname, other.fullname) &&
                Arrays.equals(avata, other.avata) &&
                Objects.equals(day_of_birth, other.day_of_birth) &&
                Objects.equals(gender, other.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client_id, fullname, Arrays.hashCode(avata), day_of_birth, gender);
    }
}
