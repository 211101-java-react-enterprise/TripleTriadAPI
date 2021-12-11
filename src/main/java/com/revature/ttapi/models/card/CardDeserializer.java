package com.revature.ttapi.models.card;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

public class CardDeserializer extends StdDeserializer<Card> {

    public CardDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Card deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
        JsonNode cardNode = jp.getCodec().readTree(jp);
        Card card = new Card();
        SimpleModule smodule = new SimpleModule();
        smodule.addDeserializer(Stats.class, new StatsDeserializer(Stats.class));
        card.setId(cardNode.get("id").asInt());
        card.setName(cardNode.get("name").textValue());
        card.setStars(cardNode.get("stars").asInt());
        card.setDescription(cardNode.get("description").textValue());
        card.setStats(new ObjectMapper()
                .readerFor(Stats.class)
                .readValue(cardNode.get("stats").get("numeric")));
        return card;
    }
}
