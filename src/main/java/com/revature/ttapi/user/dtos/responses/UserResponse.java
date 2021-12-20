package com.revature.ttapi.user.dtos.responses;

import com.revature.ttapi.user.models.AppUser;
import com.revature.ttapi.user.models.UserProfile;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class UserResponse implements Serializable {

    private UUID id;

    private String username;

    private AppUser.AccountType accountType;

    private Date creation_date;

    private Date last_updated;

    private UserProfile userProfile;

    public UserResponse() {
        super();
    }


    public UserResponse(AppUser record) {
        this.id = record.getId();
        this.username = record.getUsername();
        this.accountType = record.getAccountType();
        this.creation_date = record.getCreationDate();
        this.last_updated = record.getLastUpdated();
        this.userProfile = record.getUserProfile();
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", accountType=" + accountType +
                ", creation_date=" + creation_date +
                ", last_updated=" + last_updated +
                ", userProfile=" + userProfile +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponse that = (UserResponse) o;
        return id.equals(that.id) && username.equals(that.username) && accountType == that.accountType && creation_date.equals(that.creation_date) && last_updated.equals(that.last_updated) && userProfile.equals(that.userProfile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, accountType, creation_date, last_updated, userProfile);
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

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public Date getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(Date last_updated) {
        this.last_updated = last_updated;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

}
