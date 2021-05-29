package com.example.demo.ui.model.response;

public class UserRest {
    private int userId;
    private String firstNAme;
    private String secondNAme;
    private String email;

    public UserRest(int userId, String firstNAme, String secondNAme, String email) {
        this.userId = userId;
        this.firstNAme = firstNAme;
        this.secondNAme = secondNAme;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstNAme() {
        return firstNAme;
    }

    public String getSecondNAme() {
        return secondNAme;
    }

    public String getEmail() {
        return email;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setFirstNAme(String firstNAme) {
        this.firstNAme = firstNAme;
    }

    public void setSecondNAme(String secondNAme) {
        this.secondNAme = secondNAme;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}