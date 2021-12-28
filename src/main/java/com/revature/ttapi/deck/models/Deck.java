package com.revature.ttapi.deck.models;

import com.revature.ttapi.card.models.Card;
import com.revature.ttapi.common.exceptions.CardAlreadyInDeckException;
import com.revature.ttapi.common.exceptions.ResourceNotFoundException;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "deck")
public class Deck {

    @Id
    private UUID id;
    private String deckName;
    private UUID deckOwner;
    @Column(columnDefinition = "BINARY(255)")
    private ArrayList<Integer> cards;

    //For Jackson
    public Deck() {

    }

    //For Fetching
    public Deck(String deckName, UUID deckOwner) {
        this.deckName = deckName;
        this.deckOwner = deckOwner;
    }

    //For Creation of new decks
    public Deck(String deckName, UUID deckOwner, ArrayList<Integer> cards) {
        this.id = UUID.randomUUID();
        this.deckName = deckName;
        this.deckOwner = deckOwner;
        this.cards = cards;
    }

    //For setting up on either side without creating new DB object
    public Deck(UUID id, String deckName, UUID deckOwner, ArrayList<Integer> cards) {
        this.id = id;
        this.deckName = deckName;
        this.deckOwner = deckOwner;
        this.cards = cards;
    }

    public void addCard(Card card) {
        if (hasCard(card)){
            throw new CardAlreadyInDeckException("Duplicate cards not allowed in decks!");
        } else {
            cards.add(card.getId());
        }

    }

    public void addCards(Card... cards) {
        Arrays.stream(cards)
              .forEach(this::addCard);
    }

    public void removeCard(Card card) {
        if (!hasCard(card)){
            throw new ResourceNotFoundException("No matching card found in deck!");
        } else {
            cards.remove(card.getId());
        }

    }

    public void removeCards(Card... cards) {
        Arrays.stream(cards)
              .forEach(this::removeCard);
    }

    public boolean hasCard(Card card) {
        return cards.contains(card.getId());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public ArrayList<Integer> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Integer> cards) {
        this.cards = cards;
    }

    public UUID getDeckOwner() {
        return deckOwner;
    }

    public void setDeckOwner(UUID deckOwner) {
        this.deckOwner = deckOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return id.equals(deck.id) && deckName.equals(deck.deckName) && deckOwner.equals(deck.deckOwner) && cards.equals(deck.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deckName, deckOwner, cards);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "id=" + id +
                ", deckName='" + deckName + '\'' +
                ", deckOwner=" + deckOwner +
                ", cards=" + cards +
                '}';
    }
}
