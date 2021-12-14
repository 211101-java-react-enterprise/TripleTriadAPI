package com.revature.ttapi.user.dtos.requests;

import com.revature.ttapi.user.AppUser;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class EditUserRequest {

    @NotBlank
    private UUID id;
    private String email;
    private String password;
    private int mgp;

    public EditUserRequest() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = UUID.fromString(id);
    }

    public AppUser extractUser() {
        AppUser user = new AppUser();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setMgp(mgp);
        return user;
    }

    @Override
    public String toString() {
        return "EditUserRequest{" + "id='" + id + '\'' + ", email='" + email + '\'' + ", password='" + password + '\'' + '}';
    }

}
