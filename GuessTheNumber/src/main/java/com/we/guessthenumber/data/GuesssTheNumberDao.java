/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.guessthenumber.data;

import com.we.guessthenumber.model.Game;
import com.we.guessthenumber.model.Round;
import java.util.List;

/**
 *
 * @author ciruf
 */
public interface GuesssTheNumberDao {
    public Game addGame(Game gameToAdd);
    public Round addRound(Round roundToAdd);
    public boolean updateGame(Game gameToUpdate);
    public Game getGame(int gameId);
    public List<Game> getAllGames();
    public List<Round> getAllGameRounds(int gameId);
    
}
