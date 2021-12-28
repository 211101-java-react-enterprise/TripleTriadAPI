package com.revature.ttapi.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;
    private WebApplicationContext context;
    private ObjectMapper mapper;

    @Autowired
    public UserControllerTest(WebApplicationContext context, ObjectMapper mapper) {
        this.context = context;
        this.mapper = mapper;
    }

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void test_This_One_Is_Also_Convenient_To_Run_As_One() throws Exception {
        //baseline username available check, user doesn't exist yet, so it's fine
        mockMvc.perform(get("/users/usernamecheck/test"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("test");
        userRequest.setPassword("asd");
        userRequest.setMatchingPassword("asd");

        String requestPayload = mapper.writeValueAsString(userRequest);

        //create the user, saving the returned result for later
        MvcResult registered_user = mockMvc.perform(post("/users/registration").contentType("application/json").content(requestPayload))
                .andDo(print())
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.id").exists())
                .andReturn();
        assertEquals("application/json", registered_user.getResponse().getContentType());

        //this is a surprise tool that can help us later
        JsonNode userNode = mapper.readTree(registered_user.getResponse().getContentAsString());

        //username available check, user exists, so it's going to 409
        mockMvc.perform(get("/users/usernamecheck/test"))
                .andDo(print())
                .andExpect(status().is(409))
                .andReturn();

        mockMvc.perform(get("/users/get/test"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.username").isString())
                .andExpect(jsonPath("$.cardCollection").isArray())
                .andReturn();

        MvcResult fetched_user = mockMvc.perform(get("/users/get"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        assertEquals("application/json", fetched_user.getResponse().getContentType());
    }
}
