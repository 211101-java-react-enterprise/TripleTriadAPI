package com.revature.ttapi.game.dtos;

import com.revature.ttapi.card.models.Card;

public class PlayedCard {

    private Card card;
    private int gameID;
    private String player;
    private int locationPlayed;

    public PlayedCard() {}

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getLocationPlayed() {
        return locationPlayed;
    }

    public void setLocationPlayed(int locationPlayed) {
        this.locationPlayed = locationPlayed;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
