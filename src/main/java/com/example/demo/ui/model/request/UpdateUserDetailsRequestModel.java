package com.example.demo.ui.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateUserDetailsRequestModel {
    @NotNull(message = "firstName can not be null")
    @Size(min = 2, max = 30, message = "firstName should be more 2 and less 30")
    private String firstName;

    @NotNull (message = "secondName can not be null")
    @Size(min = 2, max = 30, message = "secondName should be more 2 and less 30")
    private String secondName;

    public UpdateUserDetailsRequestModel() {
    }

    public UpdateUserDetailsRequestModel(
            @NotNull(message = "firstName can not be null")
                @Size(min = 2, max = 30, message = "firstName should be more 2 and less 30") String firstName,
            @NotNull(message = "secondName can not be null")
                @Size(min = 2, max = 30, message = "secondName should be more 2 and less 30") String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }
}
