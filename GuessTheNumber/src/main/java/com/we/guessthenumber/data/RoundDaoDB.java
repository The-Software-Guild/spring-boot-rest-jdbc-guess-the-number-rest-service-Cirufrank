/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.guessthenumber.data;

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
public class RoundDaoDB implements RoundDao {
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public RoundDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Round addRound(Round round) {
        final String sql = "INSERT INTO round(gameId, guess, result) "
                + "VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(sql, 
                    Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, round.getGameId());
        statement.setInt(2, round.getGuess());
        statement.setString(3, round.getResult());
        return statement;
        
        }, keyHolder);
        
        round.setRoundId(keyHolder.getKey().intValue());
        return round;
    }
    
    @Override
    public List<Round> getAllGameRounds(int gameId) {
        final String sql = "SELECT * FROM round WHERE gameId = ?";
        return jdbcTemplate.query(sql, new RoundMapper(), gameId);
    }
    
    private static final class RoundMapper implements RowMapper<Round> {
       @Override
       public Round mapRow(ResultSet rs, int index) throws SQLException {
           Round round = new Round(rs.getInt("roundId"),
                            rs.getInt("gameId"),
           rs.getInt("guess"), rs.getTimestamp("guessTime").toLocalDateTime(),
           rs.getString("result"));
           return round;
       }
    }
    
}
