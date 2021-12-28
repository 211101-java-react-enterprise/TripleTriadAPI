package com.revature.ttapi.game.controller;

import com.revature.ttapi.game.dtos.GameResponse;
import com.revature.ttapi.game.dtos.PlayedCard;
import com.revature.ttapi.game.models.Game;

import com.revature.ttapi.game.services.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/games")
public class GameController {


    private SimpMessagingTemplate template;

    private GameService gameService;
    //TODO Maybe Use This: private SimpMessagingTemplate;

    @Autowired
    public GameController(GameService gameService, SimpMessagingTemplate template) {
        this.gameService = gameService;
        this.template = template;
    }

    //Retrieve the Game object
    @GetMapping("/{gameID}")
    @ResponseStatus(HttpStatus.OK)

    public GameResponse retrieveCurrentGame(@RequestParam int gameID) {
        //Make a function in service that calls the DAO to find the game
        return new GameResponse(gameService.getGame(gameID));
    }

    //TODO: Create game from endpoint with socket



    //Two features. We think this will Take the played move from the UI, fetch the DB game with the UI given ID,
    //Set the game up on the server, update all relevant data. Save to the DB. Sent game back to UI for updating.
//    @PostMapping("/playedCard")
//    @ResponseStatus(HttpStatus.OK)
    @MessageMapping("/play-card") // you can send stompCLient requests to this endpoint
//    @SendTo("/app/updateUI")
    public void updateUI(@RequestBody PlayedCard card) {
        gameService.cardHasBeenPlayed(card);
        GameResponse updateUIInCloud = new GameResponse(gameService.getGame(card.getGameID()));
        template.convertAndSend(updateUIInCloud);
    }

    //Original thought from demo was needed 2 functions.
//    @MessageMapping("/game")
//    @SendTo("/game/updateUI")
//    public GameResponse send(Game game) throws Exception {
//        //Our Though Process is that we take the game object from the server after updating the game
//        //Marshal it into a GameResponse
//        //And send it to the endpoint everyone is subscribed to.
//        GameResponse updateUIInCloud = new GameResponse(game);
//        return updateUIInCloud;
//    }



}

