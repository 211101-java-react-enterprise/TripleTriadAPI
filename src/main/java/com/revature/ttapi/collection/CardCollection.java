package com.revature.ttapi.collection;

import com.revature.ttapi.card.models.Card;
import com.revature.ttapi.user.models.AppUser;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "collection")
public class CardCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            nullable = false
    )
    private AppUser user;

    @OneToMany(mappedBy = "cardCollection")
    private Set<Deck> decks;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH
            })
    @JoinTable(
            name = "collection_card",
            joinColumns = @JoinColumn(
                    name = "collection_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "card_id",
                    referencedColumnName = "id"))
    private Set<Card> cards = new HashSet<>();

    @Size(min = 0, max = 9999)
    @Column(name = "mgp",
            nullable = false)
    private int mgp;

    public CardCollection() {
        super();
    }

    public CardCollection(AppUser user) {
        super();
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Set<Deck> getDecks() {
        return decks;
    }

    public void setDecks(Set<Deck> decks) {
        this.decks = decks;
    }

    public int getMgp() {
        return mgp;
    }

    public void setMgp(int mgp) {
        if (mgp <= 9999) {
            this.mgp = mgp;
        }
    }

    public void addMgp(int mgp) {
        this.mgp = (this.mgp + mgp <= 9999) ? this.mgp + mgp : 9999;

        if (this.mgp + mgp <= 9999) {
            this.mgp += mgp;
        } else {

        }
    }

    public void removeMgp(int mgp) {
        this.mgp = (this.mgp - mgp >= 0) ? this.mgp - mgp : 0;
    }

    public boolean hasMgp(int mgp) {
        return this.mgp >= mgp;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addCards(Card... cards) {
        Arrays.stream(cards)
              .forEach(this::addCard);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public void removeCards(Card... cards) {
        Arrays.stream(cards)
              .forEach(this::removeCard);
    }

    public boolean hasCard(Card card) {
        return cards.contains(card);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardCollection that = (CardCollection) o;
        return id == that.id && mgp == that.mgp && user.equals(that.user) && cards.equals(that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, cards, mgp);
    }

    @Override
    public String toString() {
        return "CardCollection{" +
                "id=" + id +
                ", user=" + user +
                ", mgp=" + mgp +
                '}';
    }
}
