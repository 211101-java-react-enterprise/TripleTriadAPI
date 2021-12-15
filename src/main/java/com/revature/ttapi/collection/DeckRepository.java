package com.revature.ttapi.collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckRepository extends CrudRepository<CardCollection, Integer> {
    @Query("select d from Deck d where d.cardCollection.id = :collectionId")
    List<Deck> findDecksByCollectionId(int collectionId);
}
