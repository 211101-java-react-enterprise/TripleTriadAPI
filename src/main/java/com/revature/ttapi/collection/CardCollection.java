package com.revature.ttapi.collection;

import com.revature.ttapi.models.card.Card;
import com.revature.ttapi.user.models.AppUser;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "card_collections")
public class CardCollection {

    @Id
    @Column(name = "card_collection_id", nullable = false)
    private UUID id;

    @OneToOne(mappedBy = "cardCollection")
    private AppUser user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "card_collection_cards",
            joinColumns = @JoinColumn(name = "card_collection_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id"))
    private Set<Card> cards;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardCollection that = (CardCollection) o;
        return id.equals(that.id) && user.equals(that.user) && cards.equals(that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, cards);
    }
}
