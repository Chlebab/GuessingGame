package com.gussing.game.GuessingGame.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "round")
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "round_id", nullable = false)
    private Integer roundId;

    @Column(name = "round_guess")
    private Integer roundGuess;

    @Column(name = "round_time")
    private Timestamp roundTime;

    @Column(name = "round_matches")
    private Integer roundMatches;

    @Column(name = "round_partial_matches")
    private Integer roundPartialMatches;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @JoinColumn(name = "game_id")
    private Game game;

    public Round() {
    }

    public Integer getRoundId() {
        return roundId;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }

    public Integer getRoundGuess() {
        return roundGuess;
    }

    public void setRoundGuess(Integer roundGuess) {
        this.roundGuess = roundGuess;
    }

    public Timestamp getRoundTime() {
        return roundTime;
    }

    public void setRoundTime(Timestamp roundTime) {
        this.roundTime = roundTime;
    }

    public Integer getRoundMatches() {
        return roundMatches;
    }

    public void setRoundMatches(Integer roundMatches) {
        this.roundMatches = roundMatches;
    }

    public Integer getRoundPartialMatches() {
        return roundPartialMatches;
    }

    public void setRoundPartialMatches(Integer roundPartialMatches) {
        this.roundPartialMatches = roundPartialMatches;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
