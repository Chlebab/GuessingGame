package com.gussing.game.GuessingGame.repository;

import com.gussing.game.GuessingGame.model.Round;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class RoundRepositoryImpl implements RoundRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Round> getAllRounds() {
        String query = "Select * from round;";
        return (List<Round>) em.createNativeQuery(query).getResultList();
    }

    @Override
    public Round getRoundById(int id) {
        return em.find(Round.class, id);
    }

    @Override
    public Round addRound(Round round) {
        em.persist(round);
        return round;
    }

    @Override
    public Void deleteRound(int id) {
        em.remove(getRoundById(id));
        return null;
    }

    @Override
    public Round updateRound(Round round) {
        em.merge(round);
        em.flush();
        return round;
    }
}
