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
public class GameDaoDBTest {
    @Autowired
    RoundDao roundDao;
    
    @Autowired
    GameDao gameDao;
    
    public GameDaoDBTest() {
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
     * Test of addGame method, of class GameDaoDB.
     */
    @Test
    public void testAddGame() {
    }

    /**
     * Test of updateGame method, of class GameDaoDB.
     */
    @Test
    public void testUpdateGame() {
    }

    /**
     * Test of getGame method, of class GameDaoDB.
     */
    @Test
    public void testGetGame() {
    }

    /**
     * Test of getAllGames method, of class GameDaoDB.
     */
    @Test
    public void testGetAllGames() {
    }
    
}
