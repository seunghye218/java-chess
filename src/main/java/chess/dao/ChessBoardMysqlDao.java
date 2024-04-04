package chess.dao;

import chess.database.JdbcTemplate;

public class ChessBoardMysqlDao implements ChessBoardDao {

    private final JdbcTemplate jdbcTemplate;

    public ChessBoardMysqlDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addChessBoard(String board) {
        jdbcTemplate.update("INSERT INTO chess_board (board) VALUES (?)", board);
    }
}
