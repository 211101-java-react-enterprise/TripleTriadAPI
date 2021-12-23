package com.revature.ttapi.deck.services;

import com.revature.ttapi.deck.models.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DeckService {

    private final DeckRepository deckRepo;

    @Autowired
    public DeckService(DeckRepository deckRepo) {
        this.deckRepo = deckRepo;
    }

    @Transactional(readOnly = true)
    public Deck findDeckByNameAndOwner(String deckName, UUID ownerID){
        return deckRepo.findDeckByDeckNameAndDeckOwner(deckName, ownerID);
    }

    @Transactional
    public void save(Deck newDeck){
        deckRepo.save(newDeck);
    }

    @Transactional
    public void delete(UUID deckId){
        deckRepo.deleteDeckById(deckId);
    }

}
