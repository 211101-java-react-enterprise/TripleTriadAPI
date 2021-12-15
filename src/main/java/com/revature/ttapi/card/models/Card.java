package com.revature.ttapi.card.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.revature.ttapi.collection.CardCollection;
import com.revature.ttapi.collection.Deck;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private int id;
    @Column(name = "name", columnDefinition = "varchar(255)")
    private String name;
    @Column(name = "description", columnDefinition = "varchar(255)")
    private String description;
    @Column(name = "stars", columnDefinition = "int")
    private int stars;
    //TODO: Map this relationship within the databse
    @Transient
    private Stats stats;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH
            })
    @JoinTable(
            name = "collection_card",
            joinColumns = @JoinColumn(
                    name = "card_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "collection_id",
                    referencedColumnName = "id"))
    private Set<CardCollection> collections = new HashSet<>();

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH
            })
    @JoinTable(
            name = "deck_card",
            joinColumns = @JoinColumn(
                    name = "card_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "deck_id",
                    referencedColumnName = "id"))
    private Set<Deck> decks = new HashSet<>();

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

    public Set<CardCollection> getCollections() {
        return collections;
    }

    public void setCollections(Set<CardCollection> collections) {
        this.collections = collections;
    }

    public Set<Deck> getDecks() {
        return decks;
    }

    public void setDecks(Set<Deck> decks) {
        this.decks = decks;
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
}
