package com.revature.ttapi.game;

import com.revature.ttapi.game.models.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameDAO extends CrudRepository<Game, Integer> {
}
