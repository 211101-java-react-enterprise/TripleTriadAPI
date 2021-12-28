package com.revature.ttapi.card.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "cards")
public class Card {
    /*
    Reference card data:
    {
  "id": 240,
  "name": "Omega",
  "description": "A self-evolving weapon originating from a distant star, Omega is able to upgrade its own functions and expand its armaments by analyzing data accumulated through combat with other powerful entities. It was research into this machine in its dormant state which allowed the ancient Allagans to achieve incredible advances in aetherochemical technologies.",
  "stars": 5,
  "patch": "4.4",
  "sell_price": 1500,
  "order_group": 0,
  "order": 223,
  "deck_order": 47,
  "number": "No. 223",
  "icon": "https://triad.raelys.com/images/cards/small/240.png",
  "image": "https://triad.raelys.com/images/cards/large/240.png",
  "link": "https://triad.raelys.com/cards/240",
  "stats": {
    "numeric": {
      "top": 6,
      "right": 9,
      "bottom": 3,
      "left": 10
    },
    "formatted": {
      "top": 6,
      "right": 9,
      "bottom": 3,
      "left": "A"
    }
  },
  "type": {
    "id": 0,
    "name": "Normal",
    "image": null
  },
  "owned": "49%",
  "sources": {
    "npcs": [
      {
        "id": 2293847,
        "resident_id": 1027210,
        "name": "Ironworks Hand",
        "difficulty": "3.52",
        "patch": "4.5",
        "link": "https://triad.raelys.com/npcs/2293847",
        "location": {
          "name": "Rhalgr's Reach",
          "region": "Gyr Abania",
          "x": 13,
          "y": 11
        },
        "quest": {
          "id": 68693,
          "name": "To Kweh under Distant Skies",
          "link": "https://www.garlandtools.org/db/#quest/68693"
        },
        "rules": [
          "Roulette"
        ],
        "rule_ids": [
          1
        ]
      }
    ],
    "packs": [],
    "drops": [
      "Raid: Alphascape V3.0",
      "Raid: Alphascape V4.0",
      "Raid: Alphascape V4.0 (Savage)"
    ],
    "purchase": null
  }
}

Keeping: ID, Name, Desc, Stars, Stats
Using numeric version of stats
     */

    private static int count = 0;

    @Id
    @Column(name = "id", columnDefinition = "serial")
    private int id;
    @Column(name = "name", columnDefinition = "varchar(255)")
    private String name;
    @Column(name = "description", columnDefinition = "varchar(1080)")
    private String description;
    @Column(name = "stars", columnDefinition = "int")
    private int stars;
    //TODO: Map this relationship within the databse
    @Transient
    private Stats stats;

    public Card() {
    }

    @JsonCreator
    public Card(@JsonProperty("id") int id,
                @JsonProperty("name") String name,
                @JsonProperty("description") String description,
                @JsonProperty("stars") int stars,
                @JsonProperty("stats") Stats stats) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stars = stars;
        this.stats = stats;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Card.count = count;
    }

    //for adding or removing some number to/from the count
    public static void addCount(int count) {
        Card.count += count;
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

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    @Override
    public String toString() {
        return "Card{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", stars=" + stars + ", stats=" + stats + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id && stars == card.stars && name.equals(card.name) && description.equals(card.description) && stats.toString().equals(card.stats.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, stars, stats.toString());
    }
}
