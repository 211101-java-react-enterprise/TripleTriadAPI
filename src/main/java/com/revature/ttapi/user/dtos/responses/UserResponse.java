package com.revature.ttapi.user.dtos.responses;

import com.revature.ttapi.user.AppUser;

import java.util.UUID;

public class UserResponse {

    private UUID userId;
    private String firstName;
    private String lastName;
    private String username;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserReponse{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

}
