package chess.dao;

import chess.database.JdbcTemplate;
import java.util.List;

public class ChessBoardMysqlDao implements ChessBoardDao {

    private final JdbcTemplate jdbcTemplate;

    public ChessBoardMysqlDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addChessBoard(String board) {
        jdbcTemplate.update("INSERT INTO chess_board (board) VALUES (?)", board);
    }

    @Override
    public List<String> findAll() {
        return jdbcTemplate.query("SELECT * FROM chess_board",
                resultSet -> resultSet.getString("board"));
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM chess_board");
    }
}
