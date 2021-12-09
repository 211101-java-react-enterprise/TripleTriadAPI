package com.revature.ttapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.ttapi.models.card.Card;
import com.revature.ttapi.models.card.Stats;
import com.revature.ttapi.services.FetchCards;

public class tripletriaddriver {

    public static void main(String[] args) {
        FetchCards f = new FetchCards();
        String cardJson = "{\"id\":240,\"name\":\"Omega\",\"description\":\"A self-evolving weapon originating from a distant star, Omega is able to upgrade its own functions and expand its armaments by analyzing data accumulated through combat with other powerful entities. It was research into this machine in its dormant state which allowed the ancient Allagans to achieve incredible advances in aetherochemical technologies.\",\"stars\":5,\"patch\":\"4.4\",\"sell_price\":1500,\"order_group\":0,\"order\":223,\"deck_order\":47,\"number\":\"No. 223\",\"icon\":\"https://triad.raelys.com/images/cards/small/240.png\",\"image\":\"https://triad.raelys.com/images/cards/large/240.png\",\"link\":\"https://triad.raelys.com/cards/240\",\"stats\":{\"numeric\":{\"top\":6,\"right\":9,\"bottom\":3,\"left\":10},\"formatted\":{\"top\":6,\"right\":9,\"bottom\":3,\"left\":\"A\"}},\"type\":{\"id\":0,\"name\":\"Normal\",\"image\":null},\"owned\":\"49%\",\"sources\":{\"npcs\":[{\"id\":2293847,\"resident_id\":1027210,\"name\":\"Ironworks Hand\",\"difficulty\":\"3.52\",\"patch\":\"4.5\",\"link\":\"https://triad.raelys.com/npcs/2293847\",\"location\":{\"name\":\"Rhalgr's Reach\",\"region\":\"Gyr Abania\",\"x\":13,\"y\":11},\"quest\":{\"id\":68693,\"name\":\"To Kweh under Distant Skies\",\"link\":\"https://www.garlandtools.org/db/#quest/68693\"},\"rules\":[\"Roulette\"],\"rule_ids\":[1]}],\"packs\":[],\"drops\":[\"Raid: Alphascape V3.0\",\"Raid: Alphascape V4.0\",\"Raid: Alphascape V4.0 (Savage)\"],\"purchase\":null}}";
        String statsJson = "{\"stats\": {\"numeric\": {\"top\": 6,\"right\": 9,\"bottom\": 3,\"left\": 10},\"formatted\": {\"top\": 6,\"right\": 9,\"bottom\": 3,\"left\": \"A\"}}}";
        Card c = null;
        Stats s = null;
        System.out.println(cardJson);
        try {
            c = f.parse(cardJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(c);
    }
}
