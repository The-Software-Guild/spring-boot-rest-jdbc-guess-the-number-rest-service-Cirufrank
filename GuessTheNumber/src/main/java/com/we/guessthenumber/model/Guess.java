/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.guessthenumber.model;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Guess the Number REST Service
 * 
 * @description This class represents a Guess and models this information
 * 
 * 
 * */
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
