/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.guessthenumber.model;

import java.time.LocalDateTime;

/**
 *
 * @author ciruf
 */
public class Round {
    
    private int roundId, gameId, guess;
    
    private LocalDateTime guessTime;
    
    private String result;
    
    public int getRoundId() {
        return roundId;
    }
    
    public int getGameId() {
        return gameId;
    }
    
    public int getGuess() {
        return guess;
    }
    
    public LocalDateTime getGuessTime() {
        return guessTime;
    }
    
    public String getResult() {
        return result;
    }
}
