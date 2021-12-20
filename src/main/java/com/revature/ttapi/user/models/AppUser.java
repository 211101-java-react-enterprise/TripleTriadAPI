package com.revature.ttapi.user.models;

import com.revature.ttapi.user.dtos.requests.EditUserRequest;
import com.revature.ttapi.user.dtos.requests.UserRequest;
import org.hibernate.annotations.NaturalId;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "app_user")
@Scope("prototype")
public class AppUser implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    @OneToOne(
            mappedBy = "appUser",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH
            }
    )
    @PrimaryKeyJoinColumn
    private UserProfile userProfile;

    //TODO Change to string
    @Column(columnDefinition = "BINARY(8192)")
    private ArrayList<Short> cardCollection;
    @NaturalId
    @Column(name = "username",
            unique = true,
            nullable = false)
    private String username;
    @Column(name = "password",
            nullable = false)
    private String password;
    @Column(name = "creation_date",
            nullable = false)
    private Date creationDate;
    @Column(name = "last_updated",
            nullable = false)
    private Date lastUpdated;
    @Enumerated(EnumType.STRING)
    @Column(name = "account_type",
            nullable = false)
    private AccountType accountType;

    public AppUser() {
        super();
        this.id = UUID.randomUUID();
        this.cardCollection = new ArrayList<>();
        this.accountType = AccountType.BASIC;
        this.creationDate = new Date();
        this.lastUpdated = new Date();
    }

    public AppUser(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    public static AppUser fromRequest(EditUserRequest editRequest) {
        AppUser newUser = new AppUser(
                editRequest.getUsername(),
                editRequest.getPassword()
        );
        newUser.setId(editRequest.getId());
        newUser.setAccountType(editRequest.getAccountType());
        return newUser;
    }

    public static AppUser fromRequest(UserRequest userRequest) {
        return new AppUser(
                userRequest.getUsername(),
                userRequest.getPassword()
        );
    }

    public UUID getId() {
        return this.id;
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

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public void addCard(int cardID){
        if(this.cardCollection.contains((short) cardID)){
            return;
        }
        this.cardCollection.add((short) cardID);
        Collections.sort(this.cardCollection);
    }

    public ArrayList<Short> getCardCollection() {
        return cardCollection;
    }

    public void setCardCollection(ArrayList<Short> cardCollection) {
        this.cardCollection = cardCollection;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @PrePersist
    public void onPersist() {
        this.creationDate = new Date();
    }

    @PreUpdate
    public void onUpdate() {
        this.lastUpdated = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return id.equals(appUser.id) && userProfile.equals(appUser.userProfile) && cardCollection.equals(appUser.cardCollection) && username.equals(appUser.username) && password.equals(appUser.password) && creationDate.equals(appUser.creationDate) && lastUpdated.equals(appUser.lastUpdated) && accountType == appUser.accountType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userProfile, cardCollection, username, password, creationDate, lastUpdated, accountType);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", userProfile=" + userProfile +
                ", cardCollection=" + cardCollection +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", creationDate=" + creationDate +
                ", lastUpdated=" + lastUpdated +
                ", accountType=" + accountType +
                '}';
    }

    public enum AccountType {
        ADMIN,
        DEV,
        BASIC,
        PREMIUM,
        LOCKED,
        BANNED
    }
}
