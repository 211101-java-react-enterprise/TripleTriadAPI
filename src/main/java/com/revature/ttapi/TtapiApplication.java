package com.revature.ttapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.ttapi.card.models.Card;
import com.revature.ttapi.card.services.FetchCards;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class TtapiApplication {
    public static void main(String[] args) {
                FetchCards f = new FetchCards();

                Card[] a = new Card[0];

                try {
                    a = f.generateArray();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
        SpringApplication.run(TtapiApplication.class, args);
    }
}
