/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.guessthenumber.model;

/**
 *
 * @author ciruf
 */
public class Guess {
    private int gameId, guess;
    public Guess(int gameId, int guess) {
        this.gameId = gameId;
        this.guess = guess;
    }
    
    public int getGameId() {
        return gameId;
    }
    
    public int getGuess() {
        return guess;
    }
}
