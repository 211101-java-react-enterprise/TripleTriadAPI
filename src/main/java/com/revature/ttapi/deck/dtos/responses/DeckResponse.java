package com.revature.ttapi.deck.dtos.responses;


import com.revature.ttapi.deck.dtos.models.Deck;


import java.util.ArrayList;
import java.util.UUID;

public class DeckResponse {

    private UUID id;
    private UUID deckOwner;
    private String deckName;
    private ArrayList<Integer> cards;

    public DeckResponse() {

    }

    public DeckResponse(Deck deck) {
        this.id = deck.getId();
        this.deckOwner = deck.getDeckOwner();
        this.cards = deck.getCards();
        this.deckName = deck.getDeckName();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getDeckOwner() {
        return deckOwner;
    }

    public void setDeckOwner(UUID deckOwner) {
        this.deckOwner = deckOwner;
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

    @Override
    public String toString() {
        return "DeckResponse{" +
                "id=" + id +
                ", deckOwner=" + deckOwner +
                ", deckName='" + deckName + '\'' +
                ", cards=" + cards +
                '}';
    }
}
