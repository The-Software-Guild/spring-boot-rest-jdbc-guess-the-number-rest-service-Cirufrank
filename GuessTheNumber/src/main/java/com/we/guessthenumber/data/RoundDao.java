/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.we.guessthenumber.data;

import com.we.guessthenumber.model.Round;
import java.util.List;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Guess the Number REST Service
 * 
 * @description This interface defines the methods for creating, 
 * reading, and deleting round information
 * 
 * 
 * */

public interface RoundDao {
    /**
     * Adds a new round to storage
     *
     * @param Round Round object instance to add to 
     * storage
     * @return Round added
     */
    public Round addRound(Round roundToAdd);
    /**
     * Retrieves all games from storage that belong 
     * to a certain game
     *
     * @param int id of the game the rounds belong to
     * @return List<Round> list of Round objects retrieved from 
     * storage that have a gameId member matching the game specified
     */
    public List<Round> getAllGameRounds(int gameId);
    /**
     * Deletes a Round from storage
     *
     * @param int id f the Round to delete
     * @return void
     */
    public void deleteRoundById(int roundId);
}
