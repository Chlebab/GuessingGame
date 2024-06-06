package com.gussing.game.GuessingGame.repository;

import com.gussing.game.GuessingGame.model.Game;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository {
    List<Game> getAllGames();
    Game getGameById(int id);
    Void addGame(Game game);
    Void deleteGame(int id);
    Game updateGame(Game game);
}
