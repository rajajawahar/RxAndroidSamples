package com.silicon.rxjavaexample.model;

public class Trust {

    public int id;
    public String facebookConnection;
    public String linkedInConnection;
    public String OfficiaEmail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFacebookConnection() {
        return facebookConnection;
    }

    public void setFacebookConnection(String facebookConnection) {
        this.facebookConnection = facebookConnection;
    }

    public String getLinkedInConnection() {
        return linkedInConnection;
    }

    public void setLinkedInConnection(String linkedInConnection) {
        this.linkedInConnection = linkedInConnection;
    }

    public String getOfficiaEmail() {
        return OfficiaEmail;
    }

    public void setOfficiaEmail(String officiaEmail) {
        OfficiaEmail = officiaEmail;
    }

    @Override
    public String toString() {
        return "Trust{" +
                "id=" + id +
                ", facebookConnection='" + facebookConnection + '\'' +
                ", linkedInConnection='" + linkedInConnection + '\'' +
                ", OfficiaEmail='" + OfficiaEmail + '\'' +
                '}';
    }
}
