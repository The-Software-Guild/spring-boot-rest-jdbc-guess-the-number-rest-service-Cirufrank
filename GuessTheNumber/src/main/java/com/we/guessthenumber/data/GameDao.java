/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.we.guessthenumber.data;

import com.we.guessthenumber.model.Game;
import java.util.List;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Guess the Number REST Service
 * 
 * @description This interface defines the method publicly available to 
 * other parts of our application for creating, reading, updating, and 
 * deleting information
 * 
 * 
 * */
public interface GameDao {
    /**
     * Adds a game to storage
     *
     * @param Game game object instance containing the information
     * to save about the game
     * @return Game created
     */
    public Game addGame(Game gameToAdd);
    /**
     * Updates a game existing within storage
     *
     * @param Game game object instance containing the information
     * to update about the game
     * @return boolean true if the game was updated and false otherwise
     */
    public boolean updateGame(Game gameToUpdate);
    /**
     * Retrieves a game existing within storage
     *
     * @param int id of the game to retrieve
     * @return Game game retrieved
     */
    public Game getGame(int gameId);
    /**
     * Retrieves all games stored
     * @param N/A
     * @return List<Game> a list of all games retrieved
     */
    public List<Game> getAllGames();
    /**
     * Deletes a game from storage
     * @param int id of the game to delete
     * @return void
     */
    public void deleteGameById(int gameId);
}
