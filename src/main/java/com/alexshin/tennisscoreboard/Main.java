package com.alexshin.tennisscoreboard;


import com.alexshin.tennisscoreboard.model.dto.MatchDTO;
import com.alexshin.tennisscoreboard.model.entity.Match;
import com.alexshin.tennisscoreboard.model.entity.Player;
import com.alexshin.tennisscoreboard.repository.MatchesRepository;
import com.alexshin.tennisscoreboard.repository.PlayersRepository;
import com.alexshin.tennisscoreboard.service.FinishedMatchesPersistenceService;
import com.alexshin.tennisscoreboard.service.MatchScoreCalculationService;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static com.alexshin.tennisscoreboard.util.ParseParams.parsePageNum;
import static com.alexshin.tennisscoreboard.util.ParseParams.parsePlayerFilter;

public class Main {
    public static void main(String[] args) {


//        Player player1 = new Player("Alkaras");
//        Player player2 = new Player("Daniil Medvedev");
//        var playersReository = new PlayersRepository();
//        var matchesReository = new MatchesRepository();
//        FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
//        List<Match> matches = finishedMatchesPersistenceService.findMatches(1);
//        System.out.println(" ");
//        playersReository.save(player1);
//        playersReository.saveOrGet(player2);
//
//        MatchDTO match = new MatchDTO(player1, player2);
//        match.setWinnerByNum(1);
//
//
////        var scoreService = new MatchScoreCalculationService();
//        var modelMapper = new ModelMapper();
//        var matchEntity = modelMapper.map(match, Match.class);
//
//
//        matchesReository.save(matchEntity);
//        System.out.println(matchEntity);





    }
}
