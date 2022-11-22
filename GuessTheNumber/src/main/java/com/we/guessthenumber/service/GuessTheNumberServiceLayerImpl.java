/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.guessthenumber.service;

import com.we.guessthenumber.model.Game;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 *
 * @author ciruf
 */
public class GuessTheNumberServiceLayerImpl implements GuessTheNumberServiceLayer {
    
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
    
    @Override
    public void getGamesWithConditionalMasking(List<Game> allGames) {
//        final List<Game> gamesWithMasking = new ArrayList<>();
//        for (Game currentGame: allGames) {
//            if (currentGame.getInProgress() == true) {
//                currentGame.
//            }
//        }
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
    
    
    
}
