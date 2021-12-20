package com.revature.ttapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TtapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(TtapiApplication.class, args);
    }
    /*TODO: Model a MGP to Pack Buy System
        Websocket front end work with Wezley to get it clarified so Alex and I can test the subscribing.
        Deck model creation and working features
            --Decks Model Thoughts: Array of Ints, List of Cards
            --Simple thought: Deck Id(UUID or String Name), 6 Fields that list Card IDs
                --Fetch the Deck name, setup list/array to send to game object before starting game on front end.
        Then after front end is buttoned in, launch to DB

        TODO: Major Git Flow cleanup so we don't accidentally merge old depreciated branches
    */
}
