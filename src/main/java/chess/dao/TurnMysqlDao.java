package chess.dao;

import chess.database.JdbcTemplate;

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
}
