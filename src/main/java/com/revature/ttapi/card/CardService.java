package com.revature.ttapi.card;

import com.revature.ttapi.card.dtos.requests.NewCardRequest;
import com.revature.ttapi.card.dtos.responses.CardResponse;
import com.revature.ttapi.card.models.Card;
import com.revature.ttapi.common.dtos.ResourceCreationResponse;
import com.revature.ttapi.common.exceptions.InvalidRequestException;
import com.revature.ttapi.common.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {

    private final CardRepository cardRepo;

    @Autowired
    public CardService(CardRepository cardRepo) {
        this.cardRepo = cardRepo;
    }

    @Transactional(readOnly = true)
    public List<CardResponse> findAllCards() {

        List<CardResponse> cards = ((Collection<Card>) cardRepo.findAll())
                .stream()
                .map(CardResponse::new)
                .collect(Collectors.toList());

        if (cards.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        return cards;
    }

    @Transactional(readOnly = true)
    public List<CardResponse> findAllCardsByCollectionId(int collectionId) {

        List<CardResponse> cards = ((Collection<Card>) cardRepo.findAll())
                .stream()
                .map(CardResponse::new)
                .collect(Collectors.toList());

        if (cards.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        return cards;

    }

    @Transactional(readOnly = true)
    public List<CardResponse> findCardsByStars(int stars) {

        if (stars < 1 || stars > 5) {
            throw new InvalidRequestException("Invalid owner id provided!");
        }

        List<CardResponse> cards = cardRepo.findCardsByStars(stars)
                                           .stream()
                                           .map(CardResponse::new)
                                           .collect(Collectors.toList());

        if (cards.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        return cards;

    }

    @Transactional
    public ResourceCreationResponse createNewCard(@Valid NewCardRequest newCardRequest) {

        Card newCard = new Card();
        newCard.setName(newCardRequest.getName());
        newCard.setDescription(newCardRequest.getDescription());
        newCard.setStars(newCardRequest.getStars());
        cardRepo.save(newCard);

        return new ResourceCreationResponse(newCard.getId());

    }

    public boolean isCardValid(Card card) {
        if (card == null) return false;
        if (card.getName() == null || card.getName()
                                          .trim()
                                          .equals("")) return false;
        if (card.getDescription() == null || card.getDescription()
                                                 .trim()
                                                 .equals("")) return false;
        return (card.getStars() < 1 || (card.getStars() > 5));
    }

}

