package com.revature.ttapi.card.services;

import com.revature.ttapi.card.CardRepository;
import com.revature.ttapi.card.dtos.responses.CardResponse;
import com.revature.ttapi.card.models.Card;
import com.revature.ttapi.card.models.Stats;
import com.revature.ttapi.common.exceptions.InvalidRequestException;
import com.revature.ttapi.common.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CardServiceTest {

    private CardService sut;
    @MockBean
    private CardRepository mRepo;


    @Autowired
    public CardServiceTest(CardRepository mRepo) {
        this.mRepo = mRepo;
    }

    @BeforeEach
    public void setup() {
        this.sut = new CardService(mRepo);
    }

    @Test
    void test_findAllCards_valid(){
        List<Card> l = new ArrayList<>();
        Card cardA = new Card();
        cardA.setId(240);
        cardA.setDescription("A self-evolving weapon originating from a distant star, Omega is able to upgrade its own functions and expand its armaments by analyzing data accumulated through combat with other powerful entities. It was research into this machine in its dormant state which allowed the ancient Allagans to achieve incredible advances in aetherochemical technologies.");
        cardA.setStars(5);
        cardA.setName("Omega");
        Stats s = new Stats();
        s.setBottom(3);
        s.setLeft(10);
        s.setRight(9);
        s.setTop(6);
        cardA.setStats(s);
        Card cardMan = new Card();
        cardMan.setId(240);
        cardMan.setDescription("A self-evolving weapon originating from a distant star, Omega is able to upgrade its own functions and expand its armaments by analyzing data accumulated through combat with other powerful entities. It was research into this machine in its dormant state which allowed the ancient Allagans to achieve incredible advances in aetherochemical technologies.");
        cardMan.setStars(5);
        cardMan.setName("Omega");
        cardMan.setStats(s);
        l.add(cardMan);
        l.add(cardA);
        when(mRepo.findAll()).thenReturn(l);

        List<CardResponse> test = sut.findAllCards();

        assertFalse(test.isEmpty());
        assertTrue(test.get(0) instanceof CardResponse);
    }

    @Test
    void test_findAllCards_invalid(){
        List<Card> l = new ArrayList<>();

        when(mRepo.findAll()).thenReturn(l);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            List<CardResponse> test = sut.findAllCards();
        });
    }

    @Test
    void test_findCardsByStars_valid(){
        List<Card> l = new ArrayList<>();
        Card cardA = new Card();
        cardA.setId(240);
        cardA.setDescription("A self-evolving weapon originating from a distant star, Omega is able to upgrade its own functions and expand its armaments by analyzing data accumulated through combat with other powerful entities. It was research into this machine in its dormant state which allowed the ancient Allagans to achieve incredible advances in aetherochemical technologies.");
        cardA.setStars(5);
        cardA.setName("Omega");
        Stats s = new Stats();
        s.setBottom(3);
        s.setLeft(10);
        s.setRight(9);
        s.setTop(6);
        cardA.setStats(s);
        Card cardMan = new Card();
        cardMan.setId(240);
        cardMan.setDescription("A self-evolving weapon originating from a distant star, Omega is able to upgrade its own functions and expand its armaments by analyzing data accumulated through combat with other powerful entities. It was research into this machine in its dormant state which allowed the ancient Allagans to achieve incredible advances in aetherochemical technologies.");
        cardMan.setStars(5);
        cardMan.setName("Omega");
        cardMan.setStats(s);
        l.add(cardMan);
        l.add(cardA);
        when(mRepo.findCardsByStars(5)).thenReturn(l);

        List<CardResponse> test = sut.findCardsByStars(5);

        assertFalse(test.isEmpty());
        assertTrue(test.get(0) instanceof CardResponse);
    }

    @Test
    void test_findCardsByStars_invalid(){
        List<Card> l = new ArrayList<>();

        when(mRepo.findCardsByStars(anyInt())).thenReturn(l);

        Exception exception1 = assertThrows(InvalidRequestException.class, () -> {
            List<CardResponse> test = sut.findCardsByStars(0);
        });
        Exception exception2 = assertThrows(InvalidRequestException.class, () -> {
            List<CardResponse> test = sut.findCardsByStars(6);
        });
        Exception exception3 = assertThrows(ResourceNotFoundException.class, () -> {
            List<CardResponse> test = sut.findCardsByStars(5);
        });

        String expectedMessage = "Invalid star number provided! : ";

        assertTrue(exception1.getMessage().contains(expectedMessage + "0"));
        assertTrue(exception2.getMessage().contains(expectedMessage + "6"));
    }

    @Test
    void test_CardValidation(){
        try(MockedStatic<Card> utilities = Mockito.mockStatic(Card.class)) {
                utilities.when(Card::getCount).thenReturn(346);
                assertEquals(346, Card.getCount());
                Card c = null;
                assertFalse(sut.isCardValid(c));
                c = new Card();
                assertFalse(sut.isCardValid(c));
                c.setId(0);
                assertFalse(sut.isCardValid(c));
                c.setId(Card.getCount());
                assertFalse(sut.isCardValid(c));
                c.setId(25);
                assertFalse(sut.isCardValid(c));
                c.setName(null);
                assertFalse(sut.isCardValid(c));
                c.setName("   ");
                assertFalse(sut.isCardValid(c));
                c.setName("a");
                assertFalse(sut.isCardValid(c));
                c.setDescription(null);
                assertFalse(sut.isCardValid(c));
                c.setDescription("        ");
                assertFalse(sut.isCardValid(c));
                c.setDescription("asd");
                assertFalse(sut.isCardValid(c));
                c.setStars(0);
                assertFalse(sut.isCardValid(c));
                c.setStars(6);
                assertFalse(sut.isCardValid(c));
                c.setStars(5);
                System.out.println(c);
                assertTrue(sut.isCardValid(c));
        }
    }
}
