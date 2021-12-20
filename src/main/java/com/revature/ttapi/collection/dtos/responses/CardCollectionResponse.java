package com.revature.ttapi.collection.dtos.responses;

import com.revature.ttapi.card.dtos.responses.CardResponse;
import com.revature.ttapi.collection.CardCollection;
import com.revature.ttapi.user.dtos.responses.UserResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CardCollectionResponse {

    private int collectionId;
    private UserResponse collectionOwner;
    private List<DeckResponse> collectionDecks;
    private List<CardResponse> collectionCards;

    public CardCollectionResponse() {
        super();
    }

    public CardCollectionResponse(CardCollection cardCollection) {
        this.collectionId = cardCollection.getId();
        this.collectionOwner = new UserResponse(cardCollection.getUser());
        this.collectionDecks = cardCollection.getDecks()
                                             .stream()
                                             .map(DeckResponse::new)
                                             .collect(Collectors.toList());
        this.collectionCards = cardCollection.getCards()
                                             .stream()
                                             .map(CardResponse::new)
                                             .collect(Collectors.toList());
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public UserResponse getCollectionOwner() {
        return collectionOwner;
    }

    public void setCollectionOwner(UserResponse collectionOwner) {
        this.collectionOwner = collectionOwner;
    }

    public List<DeckResponse> getCollectionDecks() {
        return collectionDecks;
    }

    public void setCollectionDecks(List<DeckResponse> collectionDecks) {
        this.collectionDecks = collectionDecks;
    }

    public List<CardResponse> getCollectionCards() {
        return collectionCards;
    }

    public void setCollectionCards(List<CardResponse> collectionCards) {
        this.collectionCards = collectionCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardCollectionResponse that = (CardCollectionResponse) o;
        return collectionId == that.collectionId && collectionOwner.equals(that.collectionOwner) && collectionDecks.equals(that.collectionDecks) && collectionCards.equals(that.collectionCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collectionId, collectionOwner, collectionDecks, collectionCards);
    }

    @Override
    public String toString() {
        return "CardCollectionResponse{" +
                "collectionId=" + collectionId +
                ", collectionOwner=" + collectionOwner +
                ", collectionDecks=" + collectionDecks +
                ", collectionCards=" + collectionCards +
                '}';
    }
}
