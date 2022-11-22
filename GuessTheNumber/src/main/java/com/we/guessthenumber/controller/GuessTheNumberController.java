/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.guessthenumber.controller;

import com.we.guessthenumber.data.GuessTheNumberDao;
import com.we.guessthenumber.model.Game;
import com.we.guessthenumber.model.Guess;
import com.we.guessthenumber.model.Round;
import com.we.guessthenumber.service.GuessTheNumberServiceLayer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ciruf
 */
@RestController
@RequestMapping("/api/guess-the-number")
public class GuessTheNumberController {
    
    final private GuessTheNumberServiceLayer service;
    @Autowired
    public GuessTheNumberController(GuessTheNumberServiceLayer service) {
        this.service = service;
    }
    
    @GetMapping("/game")
    public List<Game> getAllGames() {
        return service.getGamesWithConditionalMasking();
    }
    
    @GetMapping("/game/{gameId}")
    public ResponseEntity<Game> findGameById(@PathVariable int gameId) {
        Game result = service.getGame(gameId);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    
//    @PutMapping("game/{gameId}")
//    public ResponseEntity update(@PathVariable int gameId, @RequestBody Game game) {
//        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
//        if (gameId != game.getGameId()) {
//            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
//            
//        } else if (service.updateGame(game)) {
//            response = new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
//        
//        return response;
//    }
    
    @GetMapping("rounds/{gameId}")
    public List<Round> getAllGameRounds(@PathVariable int gameId) {
        return service.getAllGameRounds(gameId);
    }
    
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int createGame() {
      final Game newGame = service.beginGame();
      return newGame.getGameId();
    }
    
    @PostMapping("/guess")
    public ResponseEntity<Round> makeGuess(@RequestBody Guess guess) {
        final Round roundCreated = service.makeGuess(guess.getGuess(), guess.getGameId());
        if (roundCreated == null) {
            return new ResponseEntity(roundCreated, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(roundCreated, HttpStatus.CREATED);
    }
    
    
    
    
}
