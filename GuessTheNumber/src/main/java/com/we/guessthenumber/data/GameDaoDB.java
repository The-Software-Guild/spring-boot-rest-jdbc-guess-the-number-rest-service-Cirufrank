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
 * @author ciruf
 */
@Repository
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
