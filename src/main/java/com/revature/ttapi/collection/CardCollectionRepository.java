package com.revature.ttapi.collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardCollectionRepository extends JpaRepository<CardCollection, Integer> {
    Optional<CardCollection> findByUserId(UUID userId);
}
