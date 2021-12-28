package com.revature.ttapi.card;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ttapi.card.dtos.requests.CardRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CardControllerTest {

    private MockMvc mockMvc;
    private WebApplicationContext context;
    private ObjectMapper mapper;

    @Autowired
    public CardControllerTest(WebApplicationContext context, ObjectMapper mapper) {
        this.context = context;
        this.mapper = mapper;
    }

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void test_populateAllCards() throws Exception {
        mockMvc.perform(get("/cards/populatedb"))
                .andDo(print())
                .andExpect(status().is(201))
                .andReturn();
    }

    @Test
    void test_FetchAllCards() throws Exception {
        MvcResult result = mockMvc.perform(get("/cards/fetchall"))
                .andDo(print())
                .andExpect(status().is(201))
                .andReturn();
        assertEquals("application/json", result.getResponse().getContentType());
    }

    @Test
    void test_FetchCard() throws Exception {
        CardRequest request = new CardRequest();
        request.setId(5);

        String requestPayload = mapper.writeValueAsString(request);

        MvcResult result = mockMvc.perform(post("/cards/fetch").contentType("application/json").content(requestPayload))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").isNumber())
                .andReturn();
        assertEquals("application/json", result.getResponse().getContentType());
    }

    @Test
    void test_exception_handling() throws Exception {
        CardRequest request = new CardRequest();
        request.setId(-1);

        String requestPayload = mapper.writeValueAsString(request);

        mockMvc.perform(post("/cards/fetch").contentType("application/json").content(requestPayload))
                .andDo(print())
                .andExpect(status().is(500))
                .andReturn();
    }

}
