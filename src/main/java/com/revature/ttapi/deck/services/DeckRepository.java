package com.revature.ttapi.deck.services;


import com.revature.ttapi.deck.models.Deck;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.UUID;

@Repository
public interface DeckRepository extends CrudRepository<Deck, UUID> {
    Deck findDeckByDeckNameAndDeckOwner(String name, UUID deckOwner);
    ArrayList<Deck> findAllByDeckOwner(UUID deckOwner);
}
