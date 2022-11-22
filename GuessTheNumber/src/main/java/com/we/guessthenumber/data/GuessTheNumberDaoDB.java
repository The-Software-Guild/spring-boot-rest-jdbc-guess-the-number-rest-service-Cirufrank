/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.guessthenumber.data;

import com.we.guessthenumber.model.Game;
import com.we.guessthenumber.model.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ciruf
 */
@Repository
public class GuessTheNumberDaoDB implements GuesssTheNumberDao {
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public GuessTheNumberDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Game addGame(Game game) {
       final String sql = "INSERT INTO game(answer, inProgress) "
               + "VALUES(?,?);";
       
       GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
       
       jdbcTemplate.update((Connection conn) -> {
           PreparedStatement statement = conn.prepareStatement(sql,
                   Statement.RETURN_GENERATED_KEYS);
           statement.setInt(1, game.getAnswer());
           statement.setInt(2, (game.getInProgress()) ? 1 : 0);
           return statement;
           
       }, keyHolder);
       
       game.setGameId(keyHolder.getKey().intValue());
       
       return game;
    }
    
    @Override
    public Round addRound(Round round) {
        final String sql = "INSERT INTO round(gameId, guess, guessTime, result) "
                + "VALUES(?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(sql, 
                    Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, round.getGameId());
        statement.setInt(2, round.getGuess());
        statement.setDate(3, round.getGuessTime());
        statement.setString(4, round.getResult());
        return statement;
        
        }, keyHolder);
        
        round.setRoundId(keyHolder.getKey().intValue());
        return round;
    }
    
    @Override public boolean updateGame(Game game) {
        final String sql = "UPDATE game SET inProgress = ? WHERE "
                + "gameId = ?";
        return jdbcTemplate.update(sql,
                game.getInProgress(),
                game.getGameId()) > 0;
    }
    
   @Override
   public Game getGame(int gameId) {
       final String sql = "SELECT gameId, answer, inProgress FROM game WHERE id = ?";
       
       return jdbcTemplate.queryForObject(sql, new GameMapper(), gameId);
   }
   
   @Override
   public List<Game> getAllGames() {
       final String sql = "SELECT * FROM game;";
       return jdbcTemplate.query(sql, new GameMapper());
   }
   
   @Override
   public List<Round> getAllGameRounds(int gameId) {
       final String sql = "SELECT * FROM round WHERE gameId = ?";
       return jdbcTemplate.query(sql, new RoundMapper(), gameId);
   }
   
   private static final class GameMapper implements RowMapper<Game> {
       
       @Override
       public Game mapRow(ResultSet rs, int index) throws SQLException {
           Game game = new Game();
           game.setGameId(rs.getInt("gameId"));
           game.setAnswer(rs.getInt("answer"));
           game.setInProgress(rs.getBoolean("inProgress"));
           return game;
       }
   }
   
   private static final class RoundMapper implements RowMapper<Round> {
       @Override
       public Round mapRow(ResultSet rs, int index) throws SQLException {
           Round round = new Round(rs.getInt("roundId"),
                            rs.getInt("gameId"),
           rs.getInt("guess"), rs.getDate("guessTime"),
           rs.getString("result"));
           
           return round;
       }
    }
}
