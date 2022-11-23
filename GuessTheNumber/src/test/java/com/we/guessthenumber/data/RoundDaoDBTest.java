/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.we.guessthenumber.data;

import com.we.guessthenumber.TestApplicationConfiguration;
import com.we.guessthenumber.model.Game;
import com.we.guessthenumber.model.Round;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Guess the Number REST Service
 * 
 * @description This test suite ensures all the main methods of the RoundDaoDB
 * are functioning correctly so that information is add, read, updated, 
 * and deleted from the database as expected
 * 
 * 
 * */

@ExtendWith(RoundParameterResolver.class)
@ExtendWith(GameParameterResolver.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
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
        List<Game> allGames = gameDao.getAllGames();
        for (Game currentGame: allGames) {
            final int gameId = currentGame.getGameId();
            List<Round> roundsForGame = roundDao.getAllGameRounds(gameId);
            for (Round currentRound: roundsForGame) {
                roundDao.deleteRoundById(currentRound.getRoundId());
            }
            gameDao.deleteGameById(gameId);
        }
    }

    /**
     * Test of addRound method, of class RoundDaoDB.
     */
    @Test
    public void testAddRound(Round round, Game game) {
        gameDao.addGame(game);
        round.setGameId(game.getGameId());
        final Round addedRound = roundDao.addRound(round);
        assertEquals(round, addedRound);
    }

    /**
     * Test of getAllGameRounds method, of class RoundDaoDB.
     */
    @Test
    public void testGetAllGameRounds(Round round, Game game) {
        final int TOTAL_ROUNDS = 2;
        gameDao.addGame(game);
        round.setGameId(game.getGameId());
        final Round round2 = new Round(game.getGameId(),1011,
                LocalDateTime.now(), "e:9:p:0");
        roundDao.addRound(round);
        roundDao.addRound(round2);
        final List<Round> allRoundsForGame = roundDao.getAllGameRounds(game.getGameId());
        assertEquals(TOTAL_ROUNDS, allRoundsForGame.size());
        assertTrue(allRoundsForGame.contains(round));
        assertTrue(allRoundsForGame.contains(round2));
    }
    
}
