/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.we.guessthenumber.data;

import com.we.guessthenumber.TestApplicationConfiguration;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
/**
 *
 * @author ciruf
 */
@ExtendWith(GameParameterResolver.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
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

    /**
     * Test of addGame, getGame, and updateGame methods, of class GameDaoDB.
     */
    @Test
    public void testAddGetUpdateGetAllGame(Game game) {
        final Game gameAdded = gameDao.addGame(game);
        final String gameAddedString = gameAdded.toString().intern();
        final Game gameRetrieved = gameDao.getGame(gameAdded.getGameId());
        assertEquals(game, gameAdded);
        assertEquals(gameAdded, gameRetrieved);
        game.setInProgress(false);
        gameDao.updateGame(game);
        final String gameUpdatedString = gameDao.getGame(game.getGameId()).toString();
        assertNotEquals(gameAddedString, gameUpdatedString);
        assertEquals(game.toString(), gameUpdatedString);
    }

    /**
     * Test of getAllGames method, of class GameDaoDB.
     */
    @Test
    public void testGetAllGames() {
        final int TOTAL_GAMES = 2, ONE_GAME = 1;
        final Game game1 = gameDao.addGame(new Game("1234", true));
        final Game game2 = gameDao.addGame(new Game("4567", true));
        final List<Game> allGames = gameDao.getAllGames();

        assertEquals(TOTAL_GAMES, allGames.size());
        
        assertTrue(allGames.contains(game1));
        assertTrue(allGames.contains(game2));
    }
    
}
