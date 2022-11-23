/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.guessthenumber.model;

import java.util.Objects;

/**
 *
 * @author ciruf
 */
public class Game {
    
    private int gameId;
    private String answer;
    
    private boolean inProgress;
    
    public Game() {
        
    }
    
    public Game(String answer) {
        this.answer = answer;
    }
    
    public Game(String answer, boolean inProgress) {
        this.answer = answer;
        this.inProgress = inProgress;
    }
    
    public Game(int gameId, String answer, boolean inProgress) {
        this.gameId = gameId;
        this.answer = answer;
        this.inProgress = inProgress;
    } 
    
    
    public int getGameId() {
        return gameId;
    }
    
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    
    public String getAnswer() {
        return answer;
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public boolean getInProgress() {
        return inProgress;
    }
    
    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.gameId;
        hash = 53 * hash + Objects.hashCode(this.answer);
        hash = 53 * hash + (this.inProgress ? 1 : 0);
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
        final Game other = (Game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.inProgress != other.inProgress) {
            return false;
        }
        return Objects.equals(this.answer, other.answer);
    } 

    @Override
    public String toString() {
        return "Game{" + "gameId=" + gameId + ", answer=" + answer + ", inProgress=" + inProgress + '}';
    }
   
    
    
}
