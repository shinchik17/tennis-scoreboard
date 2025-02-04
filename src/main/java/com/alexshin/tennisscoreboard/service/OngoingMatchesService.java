package com.alexshin.tennisscoreboard.service;


import com.alexshin.tennisscoreboard.exception.parsing.IllegalPlayerNameException;
import com.alexshin.tennisscoreboard.exception.service.NoSuchMatchException;
import com.alexshin.tennisscoreboard.model.MatchModel;
import com.alexshin.tennisscoreboard.model.entity.Match;
import com.alexshin.tennisscoreboard.model.entity.Player;
import com.alexshin.tennisscoreboard.repository.PlayersRepository;
import org.modelmapper.ModelMapper;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class OngoingMatchesService {
    private final static ConcurrentMap<UUID, MatchModel> matchesMap = new ConcurrentHashMap<>();
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
    private final PlayersRepository playersRepository = new PlayersRepository();
    private final ModelMapper mapper = new ModelMapper();


    public MatchModel createNewMatch(String player1name, String player2name){

        if (player1name.equals(player2name)) {
            throw new IllegalPlayerNameException("Player names are identical");
        }

        Player player1 = playersRepository.saveOrGet(new Player(player1name));
        Player player2 = playersRepository.saveOrGet(new Player(player2name));
        MatchModel match = new MatchModel(player1, player2, UUID.randomUUID());
        matchesMap.put(match.getUuid(), match);
        return match;
    }

    public MatchModel getCurrentMatch(UUID uuid) {
        MatchModel match = matchesMap.get(uuid);
        if (match == null) {
            throw new NoSuchMatchException("No match with uuid=" + uuid);
        }

        return match;
    }

    public void saveMatch(MatchModel match) {
        finishedMatchesPersistenceService.saveMatch(mapper.map(match, Match.class));
        matchesMap.remove(match.getUuid());
    }

}
