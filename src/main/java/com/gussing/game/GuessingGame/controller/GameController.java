package com.gussing.game.GuessingGame.controller;

import com.gussing.game.GuessingGame.entity.Game;
import com.gussing.game.GuessingGame.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class GameController {

    @Autowired
    private GameRepository gameRepository;

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

    @PostMapping("/begin")
    public ResponseEntity<Void> gameAdd() {
        Game game = new Game();
        game.setGameStatus("In Progress");
        Random random = new Random();
        int answer = random.nextInt(9000) + 1000;
        game.setGameAnswer(answer);
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

}
