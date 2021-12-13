package com.revature.ttapi.user.dtos.responses;

import com.revature.ttapi.user.AppUser;

public class UserResponse {

    private String userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
