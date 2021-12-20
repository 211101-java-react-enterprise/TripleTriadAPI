package com.revature.ttapi.card.dtos.responses;

import com.revature.ttapi.card.models.Card;

import java.util.Objects;

public class CardResponse {
    private int id;
    private String name;
    private String description;
    private int stars;

    public CardResponse() {
        super();
    }

    public CardResponse(Card card) {
        this.id = card.getId();
        this.name = card.getName();
        this.description = card.getDescription();
        this.stars = card.getStars();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardResponse that = (CardResponse) o;
        return id == that.id && stars == that.stars && name.equals(that.name) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, stars);
    }

    @Override
    public String toString() {
        return "CardResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stars=" + stars +
                '}';
    }
}
