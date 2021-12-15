package com.revature.ttapi.card;

import com.revature.ttapi.card.models.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {
    List<Card> findCardsByStars(int stars);
}