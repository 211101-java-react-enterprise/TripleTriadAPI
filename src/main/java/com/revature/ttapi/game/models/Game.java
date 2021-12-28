package com.revature.ttapi.game.models;


import com.revature.ttapi.deck.models.Deck;
import com.revature.ttapi.game.services.BoardConverter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Component
@Scope("prototype")
@Table(name="Game")
public class Game {

    @Id
    @GeneratedValue
    private int id;
    private  String player1_username;
    private  String player2_username;
    @Transient
    private Deck deck_p1;
    @Transient
    private  Deck deck_p2;
    private  String result;
    @Column(columnDefinition = "varchar(1024)")
    @Convert(converter = BoardConverter.class)
    private  Board board;
    private String currentPlayer;
    //winner or loser, set mgp

    public Game(){
    }

    public Game(String player1_username, String player2_username, Board board) {
        this.player1_username = player1_username;
        this.player2_username = player2_username;
        this.board = board;
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

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
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


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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


//TODO: To Equals and Hashcode


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id && player1_username.equals(game.player1_username) && player2_username.equals(game.player2_username) && deck_p1.equals(game.deck_p1) && deck_p2.equals(game.deck_p2) && result.equals(game.result) && board.equals(game.board) && currentPlayer.equals(game.currentPlayer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, player1_username, player2_username, deck_p1, deck_p2, result, board, currentPlayer);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", player1_username='" + player1_username + '\'' +
                ", player2_username='" + player2_username + '\'' +
                ", deck_p1=" + deck_p1 +
                ", deck_p2=" + deck_p2 +
                ", result='" + result + '\'' +
                ", board=" + board +
                ", currentPlayer='" + currentPlayer + '\'' +
                '}';
    }
}
