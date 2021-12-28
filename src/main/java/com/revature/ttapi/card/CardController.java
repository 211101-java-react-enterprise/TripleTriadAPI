package com.revature.ttapi.card;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.ttapi.card.dtos.requests.CardRequest;
import com.revature.ttapi.card.dtos.responses.CardResponse;
import com.revature.ttapi.card.models.Card;
import com.revature.ttapi.card.services.CardService;
import com.revature.ttapi.card.services.FetchCards;
import com.revature.ttapi.user.dtos.responses.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/populatedb")
    @ResponseStatus(HttpStatus.CREATED)
    @Scheduled(cron = "0 03 * * TUE", zone = "US/Central")
    public void populateAllCards() throws JsonProcessingException {
        FetchCards f = new FetchCards();
        Card[] a = f.generateArray();
        cardService.importCards(a);
    }

    @GetMapping("/fetchall")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CardResponse> fetchAllCards() throws JsonProcessingException {
        List<Card> card = cardService.getAllCards();
        if(card.isEmpty()){
            populateAllCards();
            card = cardService.getAllCards();
        }
        List<CardResponse> resp;
        resp = card.stream().map(CardResponse::new)
                .collect(Collectors.toList());
        return resp;
    }

    @PostMapping(value = "/fetch")
    @ResponseStatus(HttpStatus.OK)
    public CardResponse fetchCard(@RequestBody CardRequest request){
        return cardService.findCardById(request.getId());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void error(Exception e, HttpServletResponse resp) throws IOException {
        e.printStackTrace();
        resp.sendError(500, e.toString());
    }
}
