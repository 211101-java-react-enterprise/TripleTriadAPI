package com.revature.ttapi.card;

import com.revature.ttapi.card.models.Card;
import com.revature.ttapi.card.services.CardService;
import com.revature.ttapi.card.services.FetchCards;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/fetchall")
    @ResponseStatus(HttpStatus.CREATED)
    public void checkUsernameAvailability() {
        FetchCards f = new FetchCards();
        Card[] a = f.generateArray();
        cardService.importCards(a);
    }


}
