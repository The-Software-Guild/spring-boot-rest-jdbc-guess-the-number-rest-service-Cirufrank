/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.we.guessthenumber.data;

import com.we.guessthenumber.model.Round;
import java.util.List;

/**
 *
 * @author ciruf
 */
public interface RoundDao {
    public Round addRound(Round roundToAdd);
    public List<Round> getAllGameRounds(int gameId);
}
