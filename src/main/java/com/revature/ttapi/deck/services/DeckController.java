package com.revature.ttapi.deck.services;

import com.revature.ttapi.deck.dtos.DeckRequest;
import com.revature.ttapi.deck.models.Deck;
import com.revature.ttapi.deck.dtos.DeckResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/deck")
public class DeckController {


    private final DeckService deckService;

    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    //Fetch Deck
    @GetMapping("/fetch/{userID}/{deckName}")
    @ResponseStatus(HttpStatus.OK)
    public DeckResponse fetchDeckByDeckName(@PathVariable UUID userID, @PathVariable String deckName) {
        //go to repo and findByDeckName
        Deck fetchedDeck = deckService.findDeckByNameAndOwner(deckName, userID);
        return new DeckResponse(fetchedDeck);
    }

    //Save Deck
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public DeckResponse saveDeck(@RequestBody DeckRequest request) {
        //Create new deck object and save to repo
        Deck createdDeck = new Deck( request.getDeckName(), request.getDeckOwner(), request.getCards());
        deckService.save(createdDeck);
        return new DeckResponse(createdDeck);
    }

    //Update Deck
    //Save Deck
    @PostMapping("/update/{deckID}")
    @ResponseStatus(HttpStatus.OK)
    public DeckResponse updateDeck(@RequestBody DeckRequest request, @PathVariable UUID deckID) {
        //Create new deck object and save to repo
        Deck createdDeck = new Deck(deckID, request.getDeckName(), request.getDeckOwner(), request.getCards());
        deckService.save(createdDeck);
        return new DeckResponse(createdDeck);
    }


    //Delete Deck
    @GetMapping("/delete/{deckID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDeck(@PathVariable UUID deckID) {
        //Delete deck by ID
        deckService.delete(deckID);
    }



}
