package com.revature.ttapi.user.dtos.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.revature.ttapi.common.dtos.PasswordForm;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Component
public class UserRequest implements PasswordForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String matchingPassword;

    public UserRequest(){

    }

    @JsonCreator
    public UserRequest(@JsonProperty("username") String username,
                       @JsonProperty("password") String password,
                       @JsonProperty("matchingPassword") String matchingPassword) {
        this.username = username;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequest that = (UserRequest) o;
        return username.equals(that.username) && password.equals(that.password) && matchingPassword.equals(that.matchingPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, matchingPassword);
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", matchingPassword='" + matchingPassword + '\'' +
                '}';
    }
}
