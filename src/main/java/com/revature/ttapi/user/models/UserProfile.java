package com.revature.ttapi.user.models;

import com.revature.ttapi.user.validators.Uuid;
import com.revature.ttapi.user.validators.email.ValidEmail;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @Column(name = "user_id")
    private UUID id;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH})
    @MapsId("id")
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            columnDefinition = "uuid")
    private AppUser appUser;

    @NaturalId
    @Column(name = "email",
            unique = true,
            nullable = false)
    @ValidEmail
    private String email;

    @Column(name = "first_name",
            nullable = false)
    private String firstName;

    @Column(name = "last_name",
            nullable = false)
    private String lastName;

    @Column(name = "last_updated",
            nullable = false)
    private Date lastUpdated;

    public UserProfile() {
        super();
    }

    public UserProfile(String email) {
        this.email = email.toLowerCase()
                          .trim();
    }

    public UserProfile(String email, String firstName, String lastName) {
        this(email);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserProfile(@Uuid UUID id, String email, String firstName, String lastName) {
        this(email, firstName, lastName);
        this.id = id;
    }

    AppUser getUser() {
        return this.appUser;
    }

    public UUID getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase()
                          .trim();
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

    public Date getLastUpdated() {
        return lastUpdated;
    }

    @PreUpdate
    public void onUpdate() {
        this.lastUpdated = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return id.equals(that.id) && appUser.equals(that.appUser) && email.equals(that.email) && firstName.equals(that.firstName) && lastName.equals(that.lastName) && lastUpdated.equals(that.lastUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appUser, email, firstName, lastName, lastUpdated);
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", user=" + appUser +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
