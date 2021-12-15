package com.revature.ttapi.collection.dtos.responses;

import com.revature.ttapi.card.dtos.responses.CardResponse;
import com.revature.ttapi.collection.Deck;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DeckResponse {

    private int deckId;
    private CardCollectionResponse deckOwner;
    private List<CardResponse> deckCards;

    public DeckResponse() {
        super();
    }

    public DeckResponse(Deck deck) {
        this.deckId = deck.getId();
        this.deckOwner = new CardCollectionResponse(deck.getCardCollection());
        this.deckCards = deck.getCards()
                             .stream()
                             .map(CardResponse::new)
                             .collect(Collectors.toList());
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public CardCollectionResponse getDeckOwner() {
        return deckOwner;
    }

    public void setDeckOwner(CardCollectionResponse deckOwner) {
        this.deckOwner = deckOwner;
    }

    public List<CardResponse> getDeckCards() {
        return deckCards;
    }

    public void setDeckCards(List<CardResponse> deckCards) {
        this.deckCards = deckCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeckResponse that = (DeckResponse) o;
        return deckId == that.deckId && deckOwner.equals(that.deckOwner) && deckCards.equals(that.deckCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deckId, deckOwner, deckCards);
    }

    @Override
    public String toString() {
        return "DeckResponse{" +
                "deckId=" + deckId +
                ", collection=" + deckOwner +
                ", cards=" + deckCards +
                '}';
    }
}
