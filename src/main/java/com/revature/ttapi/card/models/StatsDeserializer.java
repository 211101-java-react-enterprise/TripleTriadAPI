package com.revature.ttapi.card.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class StatsDeserializer extends StdDeserializer<Stats> {

    public StatsDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Stats deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
        System.out.println("in deserialize");
        JsonNode statsNode = jp.getCodec()
                               .readTree(jp);
        Stats s = new Stats();
        s.setBottom(statsNode.get("numeric")
                             .get("bottom")
                             .asInt());
        s.setTop(statsNode.get("numeric")
                          .get("top")
                          .asInt());
        s.setLeft(statsNode.get("numeric")
                           .get("left")
                           .asInt());
        s.setRight(statsNode.get("numeric")
                            .get("right")
                            .asInt());
        return s;
    }
}
