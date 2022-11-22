/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.guessthenumber.service;

import com.we.guessthenumber.data.GameDao;
import com.we.guessthenumber.data.GuessTheNumberDao;
import com.we.guessthenumber.data.RoundDao;
import com.we.guessthenumber.model.Game;
import com.we.guessthenumber.model.Round;
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
 * @author ciruf
 */
@Component
public class GuessTheNumberServiceLayerImpl implements GuessTheNumberServiceLayer {
    
    final private GameDao gameDao;
    final private RoundDao roundDao;
    @Autowired
    public GuessTheNumberServiceLayerImpl(GameDao gameDao, RoundDao roundDao) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }
    
    public List<Game> getGamesWithConditionalMasking() {
        final List<Game> allGames = addGameMasking(gameDao.getAllGames());
        return allGames;
    }
    
    @Override
    public Map<Integer, Integer> findExactMatches(int answer, int guess) {
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
    
    @Override 
    public List<Integer> findPartialMatches(int answer, int guess) {
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
    
    
    private List<Game> addGameMasking(List<Game> allGames) {
        final String MASKING = "****";
        for (Game currentGame: allGames) {
            if (currentGame.getInProgress() == true) {
                currentGame.setAnswer(MASKING);
            }
        }
        return allGames;
    }
    
    @Override
    public int generateFourDigitNumber() {
        final Random randNumGenerator = new Random();
        final int fourDigitNumber = randNumGenerator.nextInt(10000);
        return fourDigitNumber;
    }
    
    @Override
    public String formatFourDigitNumber(int fourDigitNumber) {
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
    
    @Override
    public Game getGame(int gameId) {
        return gameDao.getGame(gameId);
    }
    
    @Override
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
        final int gameAnswer = Integer.parseInt(roundGame.getAnswer());
        final String result = displayExactAndPartialMatches(gameAnswer, guess);
        final Round newRound = new Round(gameId, guess, result);
        return roundDao.addRound(newRound);
    }
    
    
}
