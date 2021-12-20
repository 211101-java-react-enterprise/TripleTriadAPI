package com.revature.ttapi.game.models;

import com.revature.ttapi.card.models.Card;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Objects;

@Component
public class Board {

    private ArrayList<Node> positions;

    public Board() {
        //Instantiate List
        positions = new ArrayList<Node>(9);

        //Add Blank Nodes
        for (int i = 0; i < 9; i++) {
            positions.add(i, new Node());
        }

        //Connect Board relevant position relationships.
        setBoardRelations(positions);

    }

    //Relationship Function setup. Should be called once when a board is instantiated and only once.
    public void setBoardRelations(ArrayList<Node> positions) {
        //Node 0 Setup
        positions.get(0).setEast_Node(positions.get(1));
        positions.get(0).setSouth_Node(positions.get(3));
        //Node 1
        positions.get(1).setEast_Node(positions.get(2));
        positions.get(1).setSouth_Node(positions.get(4));
        positions.get(1).setWest_Node(positions.get(0));
        //Node 2
        positions.get(2).setSouth_Node(positions.get(5));
        positions.get(2).setWest_Node(positions.get(1));
        //Node 3
        positions.get(3).setEast_Node(positions.get(4));
        positions.get(3).setSouth_Node(positions.get(6));
        positions.get(3).setNorth_Node(positions.get(0));
        //Node 4
        positions.get(4).setEast_Node(positions.get(5));
        positions.get(4).setSouth_Node(positions.get(7));
        positions.get(4).setWest_Node(positions.get(3));
        positions.get(4).setNorth_Node(positions.get(1));
        //Node 5
        positions.get(5).setWest_Node(positions.get(4));
        positions.get(5).setSouth_Node(positions.get(8));
        positions.get(5).setNorth_Node(positions.get(2));
        //Node 6
        positions.get(6).setEast_Node(positions.get(7));
        positions.get(6).setNorth_Node(positions.get(3));
        //Node 7
        positions.get(7).setEast_Node(positions.get(8));
        positions.get(7).setNorth_Node(positions.get(4));
        positions.get(7).setWest_Node(positions.get(6));
        //Node 8
        positions.get(8).setNorth_Node(positions.get(5));
        positions.get(8).setWest_Node(positions.get(7));

    }

    public ArrayList<Node> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<Node> positions) {
        this.positions = positions;
    }

    public void setNode(int position, Card card, String owner) {
        positions.get(position).setSlotController(owner);
        positions.get(position).setPlayedCard(card);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return positions.equals(board.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positions);
    }

    @Override
    public String toString() {
        return "Board{" +
                "positions=" + positions +
                '}';
    }

}

    /*
     *   If no index based access, make array of nodes that fakes our indexes
     *  Array 0, 1, 2, 3, 4, 5, 6, 7, 8,
     *
     * 4 directions
     * Is card present
     * ownership of field
     *
     *  0 | 1 | 2
     * ------------
     *  3 | 4 | 5
     *  -----------
     *  6 | 7 | 8
     *
     * Handle Play with a Switch
     * case 4:
     *   compare function target 1
     *   compare function target 3
     *   compare function target 5
     *   compare function target 7
     *   update array position 4 to taken on board
     *
     *
     *
     *
     *
     * */


