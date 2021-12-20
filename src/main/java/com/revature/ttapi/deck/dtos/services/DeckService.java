package com.revature.ttapi.deck.dtos.services;

import com.revature.ttapi.deck.dtos.models.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeckService {

    private final DeckRepository deckRepo;

    @Autowired
    public DeckService(DeckRepository deckRepo) {
        this.deckRepo = deckRepo;
    }

    public Deck findDeckByNameAndOwner(String deckName, UUID ownerID){
        return deckRepo.findDeckByDeckNameAndDeckOwner(deckName, ownerID);
    }

    public void save(Deck newDeck){
        deckRepo.save(newDeck);
    }

    public void delete(UUID deckId){
        deckRepo.deleteDeckById(deckId);
    }

}
