package com.revature.ttapi.user;

import com.revature.ttapi.collection.CardCollection;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "app_users")
public class AppUser {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_id", columnDefinition = "uuid", nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR CHECK (LENGTH(email) > 0)")
    private String email;

    @Column(nullable = false, updatable = false, unique = true, columnDefinition = "VARCHAR(24) CHECK (LENGTH(username) >= 4)")
    private String username;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) CHECK (LENGTH(password) >= 6)")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false, columnDefinition = "VARCHAR DEFAULT 'LOCKED'")
    private AccountType accountType;

    @Range(min = 0, max = 9999)
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int mgp;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private CardCollection cardCollection;

    public UUID getId() {
        return id;
    }

    // TODO: Refactor user constructor to take a Builder so private
    // values can be set and unneeded setters can be removed.
    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getMgp() {
        return mgp;
    }

    public void setMgp(int mgp) {
        this.mgp = mgp;
    }

    public CardCollection getCardCollection() {
        return cardCollection;
    }

    public void setCardCollection(CardCollection cardCollection) {
        this.cardCollection = cardCollection;
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
