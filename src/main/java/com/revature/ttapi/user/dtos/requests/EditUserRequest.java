package com.revature.ttapi.user.dtos.requests;

import com.revature.ttapi.user.models.AppUser;

import java.util.Objects;
import java.util.UUID;

public class EditUserRequest {

    private UUID id;

    private String username;

    private String password;

    private AppUser.AccountType accountType;

    public EditUserRequest() {
        super();
        this.accountType = AppUser.AccountType.BASIC;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public AppUser.AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AppUser.AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EditUserRequest that = (EditUserRequest) o;
        return id.equals(that.id) && username.equals(that.username) && password.equals(that.password) && accountType == that.accountType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, accountType);
    }

    @Override
    public String toString() {
        return "EditUserRequest{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accountType=" + accountType +
                '}';
    }
}
