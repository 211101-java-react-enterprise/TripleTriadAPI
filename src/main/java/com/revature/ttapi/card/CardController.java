package com.revature.ttapi.card;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.ttapi.card.models.Card;
import com.revature.ttapi.card.services.CardService;
import com.revature.ttapi.card.services.FetchCards;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/fetchall")
    @ResponseStatus(HttpStatus.CREATED)
    @Scheduled(cron = "0 03 * * TUE")
    public void checkUsernameAvailability() throws JsonProcessingException {
        FetchCards f = new FetchCards();
        Card[] a = f.generateArray();
        cardService.importCards(a);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void error(JsonProcessingException e){
        e.printStackTrace();
    }
}
