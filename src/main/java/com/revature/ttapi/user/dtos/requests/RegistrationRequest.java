package com.revature.ttapi.user.dtos.requests;

import com.revature.ttapi.user.constraints.Password;
import com.revature.ttapi.user.constraints.Username;

import java.util.Objects;

public class RegistrationRequest {

    @Username
    private String username;

    @Password
    private String password;

    public RegistrationRequest() {
        super();
    }

    public RegistrationRequest(String username, String password) {
        this.username = username;
        this.password = password;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationRequest that = (RegistrationRequest) o;
        return username.equals(that.username) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "NewRegistrationRequest{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
