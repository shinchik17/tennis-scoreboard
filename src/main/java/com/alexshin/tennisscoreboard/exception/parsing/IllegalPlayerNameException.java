package com.alexshin.tennisscoreboard.exception.parsing;

public class IllegalPlayerNameException extends IllegalArgumentException{
    public IllegalPlayerNameException(String s) {
        super("Illegal player name: %s. Name should contain only letters and spaces".formatted(s));
    }
}
