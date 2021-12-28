package com.revature.ttapi.game.dtos;


import com.revature.ttapi.deck.models.Deck;
import com.revature.ttapi.game.models.Board;
import com.revature.ttapi.game.models.Game;


public class GameResponse {



    private int id;
    private  String player1_username;
    private  String player2_username;
    private Deck deck_p1;
    private Deck deck_p2;
    private Board board;
    private String currentPlayer;

    public GameResponse(){

    }


    public GameResponse(Game game) {
        this.id = game.getId();
        this.player1_username = game.getPlayer1_username();
        this.player2_username = game.getPlayer2_username();
        this.deck_p1 = game.getDeck_p1();
        this.deck_p2 = game.getDeck_p2();
        this.board = game.getBoard();
        this.currentPlayer = game.getCurrentPlayer();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayer1_username() {
        return player1_username;
    }

    public void setPlayer1_username(String player1_username) {
        this.player1_username = player1_username;
    }

    public String getPlayer2_username() {
        return player2_username;
    }

    public void setPlayer2_username(String player2_username) {
        this.player2_username = player2_username;
    }

    public Deck getDeck_p1() {
        return deck_p1;
    }

    public void setDeck_p1(Deck deck_p1) {
        this.deck_p1 = deck_p1;
    }

    public Deck getDeck_p2() {
        return deck_p2;
    }

    public void setDeck_p2(Deck deck_p2) {
        this.deck_p2 = deck_p2;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
