package com.revature.ttapi.user.dtos.responses;

import com.revature.ttapi.user.constraints.Username;
import com.revature.ttapi.user.constraints.Uuid;
import com.revature.ttapi.user.models.AppUser;
import com.revature.ttapi.user.models.UserProfile;

import java.util.Objects;
import java.util.UUID;

public class UserResponse {

    @Uuid
    private UUID id;

    @Username
    private String username;

    private UserProfile userProfile;

    public UserResponse() {
        super();
    }

    public UserResponse(AppUser record) {
        this.id = record.getId();
        this.username = record.getUsername();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponse that = (UserResponse) o;
        return id.equals(that.id) && username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
