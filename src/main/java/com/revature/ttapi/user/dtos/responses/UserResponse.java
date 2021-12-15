package com.revature.ttapi.user.dtos.responses;

import com.revature.ttapi.user.AppUser;

import java.util.UUID;

public class UserResponse {

    private UUID userId;
    private String username;
    private String email;

    public UserResponse() {
        super();
    }

    public UserResponse(AppUser creator) {
        this.userId = creator.getId();
        this.username = creator.getUsername();
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserResponse{" + "userId=" + userId + ", username='" + username + '\'' + ", email='" + email + '\'' + '}';
    }
}
