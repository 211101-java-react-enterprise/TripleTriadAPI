package com.revature.ttapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.ttapi.models.card.Card;
import com.revature.ttapi.services.FetchCards;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Arrays;

@SpringBootApplication
public class TripleTriadDriver {
    public static void main(String[] args) {
/*
        FetchCards f = new FetchCards();

        Card[] a = new Card[0];

        try {
            a = f.generateArray();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(a));
        //Count is 1 lower than the highest card number probably because of oversight in the source api
        System.out.println(Card.getCount());*/
        SpringApplication.run(TripleTriadDriver.class, args);
    }
}
