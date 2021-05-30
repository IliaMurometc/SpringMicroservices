package com.example.demo.ui.model.response;

public class UserRest {
    private String userId;
    private String firstName;
    private String secondName;
    private String email;

    public UserRest() {
    }

    public UserRest(String userId, String firstNAme, String secondName, String email) {
        this.userId = userId;
        this.firstName = firstNAme;
        this.secondName = secondName;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}