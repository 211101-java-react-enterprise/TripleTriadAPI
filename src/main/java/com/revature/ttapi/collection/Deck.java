package com.revature.ttapi.collection;

import com.revature.ttapi.card.models.Card;

import javax.persistence.*;
import java.util.Arrays;
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


    @Transient
    private Set<Card> cards;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
