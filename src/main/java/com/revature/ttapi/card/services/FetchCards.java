package com.revature.ttapi.card.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.revature.ttapi.card.models.Card;
import com.revature.ttapi.card.models.CardDeserializer;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class FetchCards {


    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private String fetch() {
        StringBuilder sb = new StringBuilder();
        String url = "https://triad.raelys.com/api/cards";
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            return readAll(rd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Card parse(String json) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Card.class, new CardDeserializer(Card.class));
        om.registerModule(module);
        return om
                .readerFor(Card.class)
                .readValue(json);
    }


    public Card[] generateArray() throws JsonProcessingException {
        String json = fetch();
        //create a JsonNode which is a Key, value searchable set
        ObjectMapper om = new ObjectMapper();
        JsonNode top = om.readTree(json);
        //Register my deserializer for the card
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Card.class, new CardDeserializer(Card.class));
        om.registerModule(module);
        //it's apparently just this easy
        System.out.println(top.get("results"));
        Card[] a = om.readValue(top.get("results")
                                   .toString(), Card[].class);
        Card.setCount(top.get("count")
                         .asInt());
        return a;
    }
}
