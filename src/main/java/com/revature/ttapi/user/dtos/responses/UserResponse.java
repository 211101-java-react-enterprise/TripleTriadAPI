package com.revature.ttapi.user.dtos.responses;

import com.revature.ttapi.user.models.AppUser;
import com.revature.ttapi.user.models.UserProfile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class UserResponse implements Serializable {

    private UUID id;

    private String username;

    private AppUser.AccountType accountType;

    private ArrayList<Short> cardCollection;

    public UserResponse() {
        super();
    }


    public UserResponse(AppUser record) {
        this.id = record.getId();
        this.username = record.getUsername();
        this.accountType = record.getAccountType();
        this.cardCollection = record.getCardCollection();
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", accountType=" + accountType +
                ", cardCollection=" + cardCollection +
                '}';
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

    public AppUser.AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AppUser.AccountType accountType) {
        this.accountType = accountType;
    }

    public ArrayList<Short> getCardCollection() {
        return cardCollection;
    }

    public void setCardCollection(ArrayList<Short> cardCollection) {
        this.cardCollection = cardCollection;
    }
}
