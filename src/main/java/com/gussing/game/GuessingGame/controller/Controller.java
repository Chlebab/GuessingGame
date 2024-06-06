package com.gussing.game.GuessingGame.controller;

import com.gussing.game.GuessingGame.model.Game;
import com.gussing.game.GuessingGame.model.Round;
import com.gussing.game.GuessingGame.repository.GameRepository;
import com.gussing.game.GuessingGame.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class Controller {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private RoundRepository roundRepository;

    // ********************* GAME ****************************

    @GetMapping("/games")
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> gameList = gameRepository.getAllGames();
        return ResponseEntity.status(HttpStatus.OK).body(gameList);
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable("id") Integer id) {
        Game game = gameRepository.getGameById(id);
        return new ResponseEntity<Game>(game, HttpStatus.OK);
    }

    @PostMapping("/game")
    public ResponseEntity<Void> gameAdd(@RequestBody Game game) {
        gameRepository.addGame(game);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/game/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable("id") Integer id) {
        gameRepository.deleteGame(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/game/update")
    public ResponseEntity<Game> updateGame(@RequestBody Game game) {
        gameRepository.updateGame(game);
        return new ResponseEntity<Game>(game, HttpStatus.OK);
    }

    // ********************* ROUND ****************************

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
