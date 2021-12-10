package com.revature.ttapi.user.dtos.requests;

import com.revature.ttapi.common.dtos.EditResourceRequest;
import com.revature.ttapi.user.AppUser;

public class EditUserRequest extends EditResourceRequest {

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
        return "EditUserRequest{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
