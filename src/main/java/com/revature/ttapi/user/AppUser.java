package com.revature.ttapi.user;

import org.hibernate.validator.constraints.Range;

import com.revature.ttapi.models.card.Card;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_users")
public class AppUser {

    @Id
    @Column(name = "user_id")
    private String id;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR CHECK (LENGTH(email) > 0)")
    private String email;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR CHECK (LENGTH(username) >= 0)")
    private String username;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR CHECK (LENGTH(password) >= 0)")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", columnDefinition = "VARCHAR DEFAULT 'LOCKED'")
    private AccountType accountType;

    @Range(min = 0, max = 9999)
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int mgp;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Card> cards = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public enum AccountType {
        ADMIN, DEV, BASIC, PREMIUM, LOCKED, BANNED
    }

}
