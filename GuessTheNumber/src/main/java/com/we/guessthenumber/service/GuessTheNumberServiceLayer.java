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
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Guess the Number REST Service
 * 
 * @description This interface declares the methods containing the 
 * business logic of our application and allows games to be initiated, 
 * guesses to generate rounds, rounds in progress to have their 
 * answers hidden when retrieved, and new game to have a random answer 
 * generated for them
 * 
 * 
 * */

public interface GuessTheNumberServiceLayer {
    
     /**
     * Retrieves a game using the gameDao and then masks the answer of 
     * that game if necessary
     *
     * @param int gameId
     * @return Game with masking applied to answer
     */
    public Game getGame(int gameId);
    
    /**
     * Retrieves all rounds associated with a certain game
     *
     * @param int gameId the rounds are associated with
     * @return List<Round> list of all rounds associated with the 
     * game specified
     */
    public List<Round> getAllGameRounds(int gameId);
    /**
     * Initiates a game by generating a random potentially 
     * four digit number (any digits not four digits will be 
     * padded with zeroes), adding a new game with its answer 
     * as this number, and then saving that game to storage
     *
     * @param none
     * @return a game object instance representing the game added
     * to storage
     */
    public Game beginGame();
    /**
     * Creates a new round for a game with the guess specified 
     * and also determines the result of the guess and 
     * adds it to the round when saved in storage
     *
     * @param int guess number representing the guess number
     * @param int gameId number representing the id of the game 
     * the round belongs to
     * @return Round new Round object instance representing the 
     * guess and result of the round
     */
    public Round makeGuess(int guess, int gameId);
    /**
     * Gets all games with their answers masked if a game is still 
     * in progress
     *
     * @param N/A
     * @return List<Game> list of all games with their answers 
     * masked if needed
     */
    public List<Game> getGamesWithConditionalMasking();
    
    
//    /**
//     * Determines all integers that are exact matches within a guess
//     *
//     * @param int four digit number representing the answer
//     * @param int four digit number representing a guess
//     * @return Map<Integer,Integer> A map object where the key is the match 
//     * and the value is the position of the number within the answer/guess
//     */
//    public Map<Integer, Integer> findExactMatches(int answer, int guess);
//    /**
//     * Determines all integers that are partial matches within a guess
//     *
//     * @param int four digit number representing the answer
//     * @param int four digit number representing a guess
//     * @return List<Integer> A List object of all partial match
//     * integers within the guess
//     */
//    public List<Integer> findPartialMatches(int answer, int guess);
//    /**
//     * Determines all integers that are partial matches within a guess
//     *
//     * @param List<Game> A list of all game objects
//     * @return List<Game> A List object of all games with any 
//     * in progress games having their answers masked
//     */
//    public List<Game> getGamesWithConditionalMasking();
//    /**
//     * Generates a four-digit number for a game
//     *
//     * @param N/A
//     * @return a randomly generated four digit number
//     */
//    public int generateFourDigitNumber();
//    /**
//     * Formats a four digit number of the game with leading 
//     * zeroes if possible
//     *
//     * @param int potentially four digit number
//     * @return String a four digit number padded with zeroes if needed
//     */
//    public String formatFourDigitNumber(int fourDigitNumber);
//    /**
//     * Retrieves a game using the gameDao and then masks the answer of 
//     * that game if necessary
//     *
//     * @param int gameId
//     * @return Game with masking applied to answer
//     */
//    public boolean updateGame(Game game);
    
   
    
    
}
