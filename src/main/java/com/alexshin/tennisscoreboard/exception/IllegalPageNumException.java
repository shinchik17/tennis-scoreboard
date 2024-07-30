package com.alexshin.tennisscoreboard.exception;

public class IllegalPageNumException extends IllegalArgumentException{
    public IllegalPageNumException(String s) {
        super("Illegal page number: %s".formatted(s));
    }
}
