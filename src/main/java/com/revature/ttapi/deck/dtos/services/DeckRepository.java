package com.revature.ttapi.deck.dtos.services;


import com.revature.ttapi.deck.dtos.models.Deck;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface DeckRepository extends CrudRepository<Deck, UUID> {
    Deck findDeckByDeckNameAndDeckOwner(String name, UUID deckOwner);
    void deleteDeckById(UUID deckId);
}
