/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.guessthenumber.service;

import com.we.guessthenumber.model.Game;
import com.we.guessthenumber.model.Round;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ciruf
 */
public interface GuessTheNumberServiceLayer {
    
    /**
     * Determines all integers that are exact matches within a guess
     *
     * @param int four digit number representing the answer
     * @param int four digit number representing a guess
     * @return Map<Integer,Integer> A map object where the key is the match 
     * and the value is the position of the number within the answer/guess
     */
    public Map<Integer, Integer> findExactMatches(int answer, int guess);
    /**
     * Determines all integers that are partial matches within a guess
     *
     * @param int four digit number representing the answer
     * @param int four digit number representing a guess
     * @return List<Integer> A List object of all partial match
     * integers within the guess
     */
    public List<Integer> findPartialMatches(int answer, int guess);
    /**
     * Determines all integers that are partial matches within a guess
     *
     * @param List<Game> A list of all game objects
     * @return List<Game> A List object of all games with any 
     * in progress games having their answers masked
     */
    public List<Game> getGamesWithConditionalMasking();
    /**
     * Generates a four-digit number for a game
     *
     * @param N/A
     * @return a randomly generated four digit number
     */
    public int generateFourDigitNumber();
    /**
     * Formats a four digit number of the game with leading 
     * zeroes if possible
     *
     * @param int potentially four digit number
     * @return String a four digit number padded with zeroes if needed
     */
    public String formatFourDigitNumber(int fourDigitNumber);
    public Game getGame(int gameId);
    public boolean updateGame(Game game);
    
    public List<Round> getAllGameRounds(int gameId);
    
    public Game beginGame();
    
    public Round makeGuess(int guess, int gameId);
    
    
    
}
