package com.gussing.game.GuessingGame.controller;

import com.gussing.game.GuessingGame.entity.Game;
import com.gussing.game.GuessingGame.entity.Round;
import com.gussing.game.GuessingGame.repository.GameRepository;
import com.gussing.game.GuessingGame.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RoundController {

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/rounds")
    public ResponseEntity<List<Round>> getAllRounds() {
        List<Round> roundList = roundRepository.getAllRounds();
        return ResponseEntity.status(HttpStatus.OK).body(roundList);
    }

    @GetMapping("/round/{id}")
    public ResponseEntity<Round> getRoundById(@PathVariable("id") Integer id) {
        Round round = roundRepository.getRoundById(id);
        return new ResponseEntity<Round>(round, HttpStatus.OK);
    }

    @PostMapping("/round")
    public ResponseEntity<Void> roundAdd(@RequestBody Round round) {
        roundRepository.addRound(round);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PostMapping("/guess/{id}/{guess}")
    public ResponseEntity<Round> guessAnswer(@PathVariable("id") Integer gameId,
                                             @PathVariable("guess") Integer guess) {
        Game game = gameRepository.getGameById(gameId);
        if (game == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Round round = new Round();
        Integer gameAnswer = game.getGameAnswer();

        int exactMatches = 0;
        int partialMatches = 0;

        String strGameAnswer = String.valueOf(gameAnswer);
        String strGuess = String.valueOf(guess);
        boolean[] matchedInGuess = new boolean[4]; // track partial matches in the guess

        // Check for exact matches
        for (int i = 0; i < 4; i++) {
            if (strGameAnswer.charAt(i) == strGuess.charAt(i)) {
                exactMatches++;
                matchedInGuess[i] = true;
            }
        }

        // Check for partial matches
        for (int i = 0; i < 4; i++) {
            if (strGameAnswer.charAt(i) != strGuess.charAt(i)) {
                for (int j = 0; j < 4; j++) {
                    if (i != j && strGameAnswer.charAt(i) == strGuess.charAt(j) && !matchedInGuess[j]) {
                        partialMatches++;
                        matchedInGuess[j] = true;
                        break;
                    }
                }
            }
        }
        if (exactMatches == 4) {
            game.setGameStatus("Finished");
        }
        gameRepository.updateGame(game);

        round.setRoundGuess(guess);
        round.setRoundMatches(exactMatches);
        round.setRoundPartialMatches(partialMatches);

        long currentTimeMills = System.currentTimeMillis();
        round.setRoundTime(new Timestamp(currentTimeMills));

        roundRepository.addRound(round);

        return new ResponseEntity<Round>(round, HttpStatus.OK);
    }

    @DeleteMapping("/round/{id}")
    public ResponseEntity<Void> deleteRound(@PathVariable("id") Integer id) {
        roundRepository.deleteRound(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/round/update")
    public ResponseEntity<Round> updateRound(@RequestBody Round round) {
        roundRepository.updateRound(round);
        return new ResponseEntity<Round>(round, HttpStatus.OK);
    }
}
