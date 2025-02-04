package com.alexshin.tennisscoreboard.model;

import com.alexshin.tennisscoreboard.model.entity.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@NoArgsConstructor
@Setter
public class MatchModel {

    private Player player1;
    private Player player2;

    private int player1Set = 0;
    private int player2Set = 0;

    private int player1Game = 0;
    private int player2Game = 0;

    private int player1Point = 0;
    private int player2Point = 0;
    
    private Player winner;

    private UUID uuid;

    public MatchModel(Player player1, Player player2, UUID uuid) {
        this.player1 = player1;
        this.player2 = player2;
        this.uuid = uuid;
    }

    public int getPlayerPoint(int playerNum) {
        return switch (playerNum) {
            case 1 -> player1Point;
            case 2 -> player2Point;
            default -> throw new IllegalArgumentException("Unexpected playerNum value: " + playerNum);
        };
    }

    public void setPlayerPoint(int playerNum, int point) {
        switch (playerNum) {
            case 1 -> player1Point = point;
            case 2 -> player2Point = point;
            default -> throw new IllegalArgumentException("Unexpected playerNum value: " + playerNum);
        }
    }

    public int getPlayerGame(int playerNum) {
        return switch (playerNum) {
            case 1 -> player1Game;
            case 2 -> player2Game;
            default -> throw new IllegalArgumentException("Unexpected playerNum value: " + playerNum);
        };
    }

    public void setPlayerGame(int playerNum, int game) {
        switch (playerNum) {
            case 1 -> player1Game = game;
            case 2 -> player2Game = game;
            default -> throw new IllegalArgumentException("Unexpected playerNum value: " + playerNum);
        }
        setPlayerPoint(1, 0);
        setPlayerPoint(2, 0);
    }

    public int getPlayerSet(int playerNum) {
        return switch (playerNum) {
            case 1 -> player1Set;
            case 2 -> player2Set;
            
            default -> throw new IllegalArgumentException("Unexpected playerNum value: " + playerNum);
        };
    }

    public void setPlayerSet(int playerNum, int set) {
        switch (playerNum) {
            case 1 -> player1Set = set;
            case 2 -> player2Set = set;
            default -> throw new IllegalArgumentException("Unexpected playerNum value: " + playerNum);
        }
        setPlayerGame(1, 0);
        setPlayerGame(2, 0);
    }

    public void setWinnerByNum(int playerNum) {
        winner = switch (playerNum) {
            case 1 -> player1;
            case 2 -> player2;
            default -> throw new IllegalArgumentException("Unexpected playerNum value: " + playerNum);
        };
    }



}
