package com.gussing.game.GuessingGame.repository;

import com.gussing.game.GuessingGame.model.Game;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class GameRepositoryImpl implements GameRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Game> getAllGames() {
        String query = "Select * from game;";
        return (List<Game>) em.createNativeQuery(query).getResultList();
    }

    @Override
    public Game getGameById(int id) {
        return em.find(Game.class, id);
    }

    @Override
    public Void addGame(Game game) {
        em.persist(game);
        return null;
    }

    @Override
    public Void deleteGame(int id) {
        em.remove(getGameById(id));
        return null;
    }

    @Override
    public Game updateGame(Game game) {
        em.merge(game);
        em.flush();
        return game;
    }
}
