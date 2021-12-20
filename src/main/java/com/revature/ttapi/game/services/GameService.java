package com.revature.ttapi.game.services;

import com.revature.ttapi.card.models.Card;
import com.revature.ttapi.game.GameDAO;
import com.revature.ttapi.game.dtos.PlayedCard;
import com.revature.ttapi.game.models.Game;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private GameDAO repository;

    public GameService(GameDAO repository) {
        this.repository = repository;
    }

    //Game Actions: Play a Card(in empty location(handle in UI), Get/Pass Gamestate
    //TODO Learn how to pass game state gracefully.
    //Update State when a player plays a card.
    //UI level close of the game results in no DB updating for MGP(Handled not here but on UI/Websocket DTO? maybe




    public Game getGame (int gameId) {
        Game game = repository.findById(gameId).get();
        game.getBoard().setBoardRelations(game.getBoard().getPositions());
        return game;
    }

    public void cardHasBeenPlayed(PlayedCard card){
        Game game = repository.findById(card.getGameID()).get();
        game.getBoard().setBoardRelations(game.getBoard().getPositions());
        game.getBoard().setNode(card.getLocationPlayed(), card.getCard(), card.getPlayer());
        game.getBoard().getPositions().get(card.getLocationPlayed()).compareRelations();
        //TODO: Figure out how to send the serialized board back and forth with socket controller
    }


}
