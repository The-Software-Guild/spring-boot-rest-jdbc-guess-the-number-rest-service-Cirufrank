/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.guessthenumber.model;

/**
 *
 * @author ciruf
 */
public class Game {
    
    private int gameId,
            answer;
    
    private boolean inProgress;
    
    public Game() {
        
    }
    
    public Game(int gameId, int answer, boolean inProgress) {
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
    
    public int getAnswer() {
        return answer;
    }
    
    public void setAnswer(int answer) {
        this.answer = answer;
    }
    
    public boolean getInProgress() {
        return inProgress;
    }
    
    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }
}
