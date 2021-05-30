package com.example.demo.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetailsRequestModel {
    @NotNull (message = "firstName can not be null")
    @Size(min = 2, max = 30, message = "firstName should be more 2 and less 30")
    private String firstName;

    @NotNull (message = "secondName can not be null")
    @Size(min = 2, max = 30, message = "secondName should be more 2 and less 30")
    private String secondName;

    @NotNull (message = "email can not be null")
    @Email
    private String email;

    @NotNull (message = "password can not be null")
    @Size(min = 8, max = 20, message = "password should be more 8 and less 20")
    private String password;

    public UserDetailsRequestModel(String firstName) {
        this.firstName = firstName;
    }

    public UserDetailsRequestModel(String firstName, String secondName, String email, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
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

    public void setPassword(String password) {
        this.password = password;
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

    public String getPassword() {
        return password;
    }
}