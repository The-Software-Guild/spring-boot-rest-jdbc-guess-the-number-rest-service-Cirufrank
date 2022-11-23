/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.guessthenumber.data;

import com.we.guessthenumber.model.Game;
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
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Guess the Number REST Service
 * 
 * @description This class implemented the GameDao interface which defines the 
 * methods publicly available to other parts of our application for creating, 
 * reading, updating, and deleting information
 * 
 * 
 * */

@Repository
//The @Repository annotation is a class-level 
//annotation that tells Spring this is an injectable 
//bean. Functionally, it's the same as @Component, but 
//@Repository carries a bit more semantic value. The 
//repository pattern presents a collection-like interface 
//for a data store. We add, update, find, get, 
//or delete without needing to know exactly how the data is 
//stored. Most DAOs are repositories (or close to it), so 
//we prefer @Repository over @Component.

public class GameDaoDB implements GameDao {
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public GameDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Game addGame(Game game) {
       final String sql = "INSERT INTO game(answer) "
               + "VALUES(?);";
       
       GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
       
       jdbcTemplate.update((Connection conn) -> {
           PreparedStatement statement = conn.prepareStatement(sql,
                   Statement.RETURN_GENERATED_KEYS);
           statement.setInt(1, Integer.parseInt(game.getAnswer()));
           return statement;
           
       }, keyHolder);
       
       game.setGameId(keyHolder.getKey().intValue());
       
       return game;
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
        final String sql = "SELECT gameId, answer, inProgress FROM game WHERE gameId = ?;";

        return jdbcTemplate.queryForObject(sql, new GameMapper(), gameId);
    }
    
    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT * FROM game;";
        return jdbcTemplate.query(sql, new GameMapper());
    }
    @Override
    public void deleteGameById(int gameId) {
        final String sql = "DELETE FROM game WHERE gameId = ?;";
        jdbcTemplate.update(sql, gameId);
    }
    
    private static final class GameMapper implements RowMapper<Game> {
       
       @Override
       public Game mapRow(ResultSet rs, int index) throws SQLException {
           Game game = new Game();
           game.setGameId(rs.getInt("gameId"));
           game.setAnswer(Integer.toString(rs.getInt("answer")));
           game.setInProgress(rs.getBoolean("inProgress"));
           return game;
       }
   }
}
