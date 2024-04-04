package chess.dao;

import chess.database.JdbcTemplate;
import java.util.List;

public class TurnMysqlDao implements TurnDao {

    private final JdbcTemplate jdbcTemplate;

    public TurnMysqlDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addTurn(String team) {
        jdbcTemplate.update("INSERT INTO turn (team) VALUES (?)", team);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM turn");
    }

    @Override
    public List<String> findAll() {
        return jdbcTemplate.query("SELECT * FROM turn",
                resultSet -> resultSet.getString("team"));
    }
}
