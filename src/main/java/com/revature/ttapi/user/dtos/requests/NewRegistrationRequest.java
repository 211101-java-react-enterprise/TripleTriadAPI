package com.revature.ttapi.user.dtos.requests;

import com.revature.ttapi.common.util.validation.Regex;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class NewRegistrationRequest {

    @Email
    private String email;

    @Size(min = 4, max = 15)
    private String username;

    @Pattern(
        regexp = Regex.PASSWORD,
        message = "Passwords must have a minimum of 8 characters, and contain at least one letter, one number, and one special character"
    )
    private String password;

    public NewRegistrationRequest() {
        super();
    }

    public NewRegistrationRequest(String email, String username, String password) {

        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "NewRegistration{" +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
