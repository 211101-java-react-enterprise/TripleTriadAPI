package com.revature.ttapi.game.models;

import com.revature.ttapi.card.models.Card;

//Customer node that stores a card played, and relevant position functions for comparing plays made. Only 9 of these nodes should ever be made in 1 game
//and that makes us able to use index based usage functions of the containing ArrayList.
public class Node {
    public Card getPlayedCard() {
        return playedCard;
    }

    public void setPlayedCard(Card playedCard) {
        this.playedCard = playedCard;
    }

    public Node getNorth_Node() {
        return north_Node;
    }

    public void setNorth_Node(Node north_Node) {
        this.north_Node = north_Node;
    }

    public Node getEast_Node() {
        return east_Node;
    }

    public void setEast_Node(Node east_Node) {
        this.east_Node = east_Node;
    }

    public Node getSouth_Node() {
        return south_Node;
    }

    public void setSouth_Node(Node south_Node) {
        this.south_Node = south_Node;
    }

    public Node getWest_Node() {
        return west_Node;
    }

    public void setWest_Node(Node west_Node) {
        this.west_Node = west_Node;
    }

    public String getSlotController() {
        return slotController;
    }


    public void setSlotController(String slotController) {
        this.slotController = slotController;
    }

    //Called to determine result of a play
    public void compareRelations(){
        //Compare to north card
        if (this.north_Node != null && (this.getPlayedCard().getStats().getTop() > this.getNorth_Node().getPlayedCard().getStats().getBottom())){
            //Change ownership if card to north
            this.getNorth_Node().setSlotController(this.getSlotController());
        }
        //Compare to east card
        if (this.east_Node != null && (this.getPlayedCard().getStats().getRight() > this.getEast_Node().getPlayedCard().getStats().getLeft())){
            //Change ownership if card to east
            this.getEast_Node().setSlotController(this.getSlotController());
        }
        //Compare to south card
        if (this.south_Node != null && (this.getPlayedCard().getStats().getBottom() > this.getSouth_Node().getPlayedCard().getStats().getTop())){
            //Change ownership if card to south
            this.getSouth_Node().setSlotController(this.getSlotController());
        }
        //Compare to west card
        if (this.west_Node != null && (this.getPlayedCard().getStats().getLeft() > this.getWest_Node().getPlayedCard().getStats().getRight())){
            //Change ownership if card to south
            this.getWest_Node().setSlotController(this.getSlotController());
        }

    }

    Card playedCard;
    Node north_Node;
    Node east_Node;
    Node south_Node;
    Node west_Node;
    String slotController;

    @Override
    public String toString() {
        String cardResult = "";
        if (playedCard == null) {
            cardResult = null;
        } else {
            cardResult = String.valueOf(playedCard.getId());
        }

        return "Node{" +
                "playedCard=" + cardResult +
                ", slotController=" + slotController +
                '}';
    }
}