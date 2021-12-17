package com.revature.ttapi.game.controller;

import com.revature.ttapi.game.dtos.GameResponse;
import com.revature.ttapi.game.dtos.PlayedCard;
import com.revature.ttapi.game.models.Game;

import com.revature.ttapi.game.services.GameService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameController {

    private GameService gameService;
    //Thoughts on controller function are that we want it to generate a link or URL that two people can hit to join a game if they are logged in. UI will handle Redirection

//    @MessageMapping("/user")
//    @SendTo("/game/user")
//    public GameResponse gamsState(GameService gameService){
//        Game testGame = new Game();
//        gameService.setGame(testGame);
//        return new GameResponse(gameService);
//    }

    //Retrieve the Game object
    @GetMapping("/{gameID}")
    @ResponseStatus(HttpStatus.OK)
    public GameResponse retrieveCurrrentGame(@RequestParam int gameID) {
        //Make a function in service that calls the DAO to find the game
        return new GameResponse(gameService.getGame(gameID));
    }

    //TODO: Create game from endpoint with socket


    //Push Update to UI
    @PostMapping("/playedCard")
    @ResponseStatus(HttpStatus.OK)
    public void updateUI(@RequestBody PlayedCard card){
        gameService.cardHasBeenPlayed(card);
    }

}

