package com.revature.ttapi.collection;

import com.revature.ttapi.collection.dtos.responses.CardCollectionResponse;
import com.revature.ttapi.collection.dtos.responses.DeckResponse;
import com.revature.ttapi.common.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CollectionService {

    private final CardCollectionRepository collectionRepo;
    private final DeckRepository deckRepo;

    @Autowired
    public CollectionService(CardCollectionRepository collectionRepo, DeckRepository deckRepo) {
        this.collectionRepo = collectionRepo;
        this.deckRepo = deckRepo;
    }

    public CardCollectionResponse getCollectionByOwnerId(UUID ownerId) {
        Optional<CardCollection> targetCollection = collectionRepo.findByUserId(ownerId);
        CardCollectionResponse response;
        if (targetCollection.isPresent()) {
            response = new CardCollectionResponse(targetCollection.get());
        } else {
            throw new ResourceNotFoundException("Collection not found for user with id: " + ownerId);
        }
        return response;
    }

    public List<DeckResponse> getDecksByCollectionId(int collectionId) {
        List<DeckResponse> responses = deckRepo.findDecksByCollectionId(collectionId)
                                               .stream()
                                               .map(DeckResponse::new)
                                               .collect(Collectors.toList());
        responses.stream()
                 .findAny()
                 .orElseThrow(() -> new ResourceNotFoundException("Deck not found for id: " + collectionId));
        return responses;
    }
}
