/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.guessthenumber.service;

import com.we.guessthenumber.data.GameDao;
import com.we.guessthenumber.data.RoundDao;
import com.we.guessthenumber.model.Game;
import com.we.guessthenumber.model.Round;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Guess the Number REST Service
 * 
 * @description This class implements the GuessTheNumberServiceLayer interface
 * and defines the methods containing the business logic of our application and 
 * allows games to be initiated, guesses to generate rounds, rounds in progress 
 * to have their answers hidden when retrieved, and new game to have a random answer 
 * generated for them
 * 
 * 
 * */

@Component
public class GuessTheNumberServiceLayerImpl implements GuessTheNumberServiceLayer {
    
    final private GameDao gameDao;
    final private RoundDao roundDao;
    @Autowired
    public GuessTheNumberServiceLayerImpl(GameDao gameDao, RoundDao roundDao) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }
    
    @Override
    public Game getGame(int gameId) {
        return addGameMasking(gameDao.getGame(gameId));
    }
    
    
    public boolean updateGame(Game game) {
        return gameDao.updateGame(game);
    }
    
    @Override
    public List<Round> getAllGameRounds(int gameId) {
        return roundDao.getAllGameRounds(gameId);
    }
    
    @Override
    public Game beginGame() {
        final int gameAnswer = generateFourDigitNumber();
        final Game createdGame = new Game(Integer.toString(gameAnswer));
        return gameDao.addGame(createdGame);
    }
    
    @Override
    public Round makeGuess(int guess, int gameId) {
        final Game roundGame = gameDao.getGame(gameId);
        if (roundGame.getInProgress() == false) return null;
        final int gameAnswer = Integer.parseInt(roundGame.getAnswer());
        final String result = displayExactAndPartialMatches(gameAnswer, guess).intern();
        checkGameStatus(roundGame, result);
        final Round newRound = new Round(gameId, guess, LocalDateTime.now(), result);
        return roundDao.addRound(newRound);
    }
    
    @Override
    public List<Game> getGamesWithConditionalMasking() {
        final List<Game> allGames = addMaskingToAllGames(gameDao.getAllGames());
        return allGames;
    }
    
    
    private Map<Integer, Integer> findExactMatches(int answer, int guess) {
        final Map<Integer, Integer> exactMatches = new HashMap<>();
        final List<Integer> answerTokens = convertIntToList(answer);
        final List<Integer> guessTokens = convertIntToList(guess);

        for (int index = 0; index < guessTokens.size(); index += 1) {
            final int curGuessDigit = guessTokens.get(index);
            final int curAnswerDigit = answerTokens.get(index);
            if (curGuessDigit == curAnswerDigit) {
                exactMatches.put(index, curGuessDigit);
            }
        }
        return exactMatches;
    }
    
    
    private List<Integer> findPartialMatches(int answer, int guess) {
        final Map<Integer, Integer> exactMatches = findExactMatches(answer, guess);
        final List<Integer> partialMatches = new ArrayList<>();
        final List<Integer> answerTokens = convertIntToList(answer);
        final List<Integer> guessTokens = convertIntToList(guess);

        for (int index = 0; index < guessTokens.size(); index += 1) {
            final int curGuessDigit = guessTokens.get(index);
            final int curAnswerDigit = answerTokens.get(index);
            final int totalExactMatchesOfDigit = 
                    new ArrayList(exactMatches.values().stream()
                    .filter(match -> match == curGuessDigit).
                    collect(Collectors.toList())).size();
            final int totalOccurencesOfDigit = new ArrayList(answerTokens.stream()
                    .filter(match -> match == curGuessDigit).
                    collect(Collectors.toList())).size();
            if (curGuessDigit == curAnswerDigit) {
                continue;
            }
            if (answerTokens.contains(curGuessDigit) &&
                    totalExactMatchesOfDigit < totalOccurencesOfDigit) {
                partialMatches.add(curAnswerDigit);
            }
        }
        
        return partialMatches;
    }
    
    private String displayExactAndPartialMatches(int answer, int guess ) {
        final int exactMatches = findExactMatches(answer, guess).values().size();
        final int partialMatches = findPartialMatches(answer, guess).size();
        return "e:" + exactMatches + ":p:" + partialMatches;
    }
    
    
    private List<Game> addMaskingToAllGames(List<Game> allGames) {
        for (Game currentGame: allGames) {
            currentGame = addGameMasking(currentGame);
        }
        return allGames;
    }
    
    private Game addGameMasking(Game game) {
        final String MASKING = "****";
            if (game.getInProgress() == true) {
                game.setAnswer(MASKING);
            }
        return game;
    }
    
    
    private int generateFourDigitNumber() {
        final Random randNumGenerator = new Random();
        final int fourDigitNumber = randNumGenerator.nextInt(10000);
        return fourDigitNumber;
    }
    
    
    private String formatFourDigitNumber(int fourDigitNumber) {
        return String.format("%04d", fourDigitNumber);
    }
    private List<Integer> convertIntToList(int integer) {
        final int FIXED_SIZE = 4;
        final List<Integer> integerTokens = Arrays.stream(Integer.toString(integer).split(""))
                                            .map(stringInt -> Integer.parseInt(stringInt))
                                            .collect(Collectors.toList());
        while(integerTokens.size() < FIXED_SIZE) {
            integerTokens.add(0, 0);
        }
        return integerTokens;
    }
     
    private void checkGameStatus(Game game, String result) {
        final String COMPLETED_GAME = "e:4:p:0".intern();
        if (result == COMPLETED_GAME) {
            game.setInProgress(false);
            gameDao.updateGame(game);
        }
    }
    
    
}
