/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.we.guessthenumber.data;

import com.we.guessthenumber.model.Game;
import com.we.guessthenumber.model.Round;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ciruf
 */
public class RoundDaoDBTest {
    
    @Autowired
    GameDao gameDao;
    
    @Autowired
    RoundDao roundDao;
    
    public RoundDaoDBTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Game> allGames = gameDao.getAllGames();
        List<Round> allRounds = new ArrayList<>();
        for (Game currentGame: allGames) {
            final int gameId = currentGame.getGameId();
            List<Round> roundsForGame = roundDao.getAllGameRounds(gameId);
            for (Round currentRound: roundsForGame) {
                roundDao.deleteRoundById(currentRound.getRoundId());
            }
            gameDao.deleteGameById(gameId);
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addRound method, of class RoundDaoDB.
     */
    @Test
    public void testAddRound() {
    }

    /**
     * Test of getAllGameRounds method, of class RoundDaoDB.
     */
    @Test
    public void testGetAllGameRounds() {
    }
    
}
