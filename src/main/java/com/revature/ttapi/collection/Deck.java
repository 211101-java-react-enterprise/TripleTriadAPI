package com.revature.ttapi.collection;

import com.revature.ttapi.card.models.Card;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "deck")
public class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(
            name = "collection_id",
            referencedColumnName = "id",
            nullable = false
    )
    private CardCollection cardCollection;

    @ManyToMany(
            mappedBy = "decks",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH
            })
    private Set<Card> cards;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CardCollection getCardCollection() {
        return cardCollection;
    }

    public void setCardCollection(CardCollection collection) {
        this.cardCollection = collection;
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
        Deck deck = (Deck) o;
        return id == deck.id && cardCollection.equals(deck.cardCollection) && cards.equals(deck.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardCollection, cards);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "id=" + id +
                ", collections=" + cardCollection +
                ", cards=" + cards +
                '}';
    }
}
