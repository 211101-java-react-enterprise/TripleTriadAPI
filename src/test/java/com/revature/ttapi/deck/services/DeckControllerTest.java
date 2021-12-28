package com.revature.ttapi.deck.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ttapi.deck.dtos.DeckRequest;
import com.revature.ttapi.user.dtos.requests.UserRequest;
import com.revature.ttapi.user.models.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class DeckControllerTest {

    private MockMvc mockMvc;
    private WebApplicationContext context;
    private ObjectMapper mapper;

    @Autowired
    public DeckControllerTest(WebApplicationContext context, ObjectMapper mapper) {
        this.context = context;
        this.mapper = mapper;
    }

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void test_I_Have_To_Do_all_Of_These_In_One_LMAO() throws Exception {
        //hoo boy this requires a lot of setup
        UserRequest request = new UserRequest();
        request.setUsername("a");
        request.setPassword("aa");
        request.setMatchingPassword("aa");

        String requestPayload = mapper.writeValueAsString(request);

        MvcResult registered_user = mockMvc.perform(post("/users/registration").contentType("application/json").content(requestPayload))
                .andDo(print())
                .andExpect(status().is(201))
                .andReturn();
        assertEquals("application/json", registered_user.getResponse().getContentType());

        String res = registered_user.getResponse().getContentAsString();
        //AppUser u = mapper.convertValue(res, AppUser.class);
        //this didn't work fml
        //Cannot construct instance of AppUser no String-argument constructor/factory method to deserialize from String value
        JsonNode cardNode = mapper.readTree(res);
        AppUser u = new AppUser();
        u.setId(UUID.fromString(cardNode.get("id").asText()));
        System.out.println(cardNode.get("id").asText());
        System.out.println(u.getId());

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(5);arr.add(6);arr.add(7);arr.add(8);arr.add(9);
        DeckRequest req = new DeckRequest();
        req.setDeckName("abc");
        req.setDeckOwner(u.getId());
        req.setCards(arr);
        requestPayload = mapper.writeValueAsString(req);

        MvcResult saved_deck = mockMvc.perform(post("/deck/save").contentType("application/json").content(requestPayload))
                .andDo(print())
                .andExpect(status().is(201))
                .andReturn();
        assertEquals("application/json", saved_deck.getResponse().getContentType());

        MvcResult fetched_deck = mockMvc.perform(get("/deck/fetch/" + u.getId() + "/abc"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").exists())
                .andReturn();
        assertEquals("application/json", fetched_deck.getResponse().getContentType());

        JsonNode deckNode = mapper.readTree(fetched_deck.getResponse().getContentAsString());

        DeckRequest deck = new DeckRequest();
        deck.setId(UUID.fromString(deckNode.get("id").asText()));
        deck.setDeckOwner(UUID.fromString(deckNode.get("deckOwner").asText()));
        deck.setDeckName(deckNode.get("deckName").asText());
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(45);arrayList.add(4);arrayList.add(5);arrayList.add(33);arrayList.add(69);
        deck.setCards(arrayList);

        requestPayload = mapper.writeValueAsString(deck);

        MvcResult updated_deck = mockMvc.perform(post("/deck/update/" + deck.getId()).contentType("application/json").content(requestPayload))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        assertEquals("application/json", registered_user.getResponse().getContentType());

        mockMvc.perform(get("/deck/delete/" + deck.getId()))
                .andDo(print())
                .andExpect(status().is(204))
                .andReturn();
    }
}
