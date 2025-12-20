package com.pl.premiere_scout.Exceptions;

public class PlayerNotFoundException extends RuntimeException{
    public PlayerNotFoundException(String error){
        super(error);
    }
}
