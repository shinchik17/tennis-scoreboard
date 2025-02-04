package com.alexshin.tennisscoreboard.service;


import com.alexshin.tennisscoreboard.model.entity.Match;
import com.alexshin.tennisscoreboard.repository.MatchesRepository;

import java.util.List;

public class FinishedMatchesPersistenceService {
    private final MatchesRepository matchesRepository = new MatchesRepository();
    public final int PAGE_SIZE = 5;


    public void saveMatch(Match match) {
        matchesRepository.save(match);
    }

    public List<Match> findMatches(int pageNum, String playerName) {
        return matchesRepository.findMatchesByPlayerName((pageNum - 1) * PAGE_SIZE, PAGE_SIZE, playerName);
    }

    public List<Match> findMatches(int pageNum) {
        return matchesRepository.findMatches((pageNum - 1) * PAGE_SIZE, PAGE_SIZE);
    }


}
