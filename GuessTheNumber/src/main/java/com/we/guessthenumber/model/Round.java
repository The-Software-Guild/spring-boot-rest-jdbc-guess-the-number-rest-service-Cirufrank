/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.guessthenumber.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author ciruf
 */
public class Round {
    
    private int roundId, gameId, guess;
    
    private LocalDateTime guessTime;
    
    private String result;
    
    public Round(int gameId, int guess, LocalDateTime guessTime, String result) {
        this.gameId = gameId;
        this.guess = guess;
        this.guessTime = guessTime;
        this.result = result;
    }
    
    public Round(int roundId, int gameId, int guess, LocalDateTime guessTime, 
            String result) {
        this.roundId = roundId;
        this.gameId = gameId;
        this.guess = guess;
        this.guessTime = guessTime;
        this.result = result;
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
    
    public void setGameId(int gameId) {
        this.gameId = gameId;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.roundId;
        hash = 59 * hash + this.gameId;
        hash = 59 * hash + this.guess;
        hash = 59 * hash + Objects.hashCode(this.guessTime);
        hash = 59 * hash + Objects.hashCode(this.result);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.roundId != other.roundId) {
            return false;
        }
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.guess != other.guess) {
            return false;
        }
        
        return Objects.equals(this.result, other.result);

    }

    @Override
    public String toString() {
        return "Round{" + "roundId=" + roundId + ", gameId=" + gameId + ", guess=" + guess + ", guessTime=" + guessTime + ", result=" + result + '}';
    }
    
    
    
    
}
