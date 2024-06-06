package com.gussing.game.GuessingGame.repository;

import com.gussing.game.GuessingGame.model.Game;
import com.gussing.game.GuessingGame.model.Round;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoundRepository {
    List<Round> getAllRounds();
    Round getRoundById(int id);
    Round addRound(Round round);
    Void deleteRound(int id);
    Round updateRound(Round round);
}
