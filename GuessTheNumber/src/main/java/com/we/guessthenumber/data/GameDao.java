/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.we.guessthenumber.data;

import com.we.guessthenumber.model.Game;
import java.util.List;

/**
 *
 * @author ciruf
 */
public interface GameDao {
    public Game addGame(Game gameToAdd);
    public boolean updateGame(Game gameToUpdate);
    public Game getGame(int gameId);
    public List<Game> getAllGames();
}
