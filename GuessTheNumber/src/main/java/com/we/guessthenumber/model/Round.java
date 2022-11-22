/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.guessthenumber.model;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 *
 * @author ciruf
 */
public class Round {
    
    private int roundId, gameId, guess;
    
    private Date guessTime;
    
    private String result;
    
    public Round(int roundId, int gameId, int guess, Date guessTime, 
            String result) {
        this.roundId = roundId;
        this.gameId = gameId;
        this.guess = guess;
        this.guessTime = guessTime;
    }
    
    public int getRoundId() {
        return roundId;
    }
    
    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }
    
    public int getGameId() {
        return gameId;
    }
    
    public int getGuess() {
        return guess;
    }
    
    public Date getGuessTime() {
        return guessTime;
    }
    
    public String getResult() {
        return result;
    }
}
