package com.silicon.rxjavaexample.model;

public class Customer {

    public int id;
    public String name;
    public String emailId;
    public String phoneNo;
    public String address;
    public Trust trust;

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Trust getTrust() {
        return trust;
    }

    public void setTrust(Trust trust) {
        this.trust = trust;
    }

    public Customer(int id, String name, String emailId, String phoneNo, String address, Trust trust) {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.phoneNo = phoneNo;
        this.address = address;
        this.trust = trust;
    }
}
