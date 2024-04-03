package chess.dao;

import chess.database.JdbcTemplate;
import chess.dto.MovementDto;
import java.util.List;

public class MovementDao {

    private final JdbcTemplate jdbcTemplate;

    public MovementDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addMovement(MovementDto movementDto) {
        jdbcTemplate.update(
                "INSERT INTO movement(turn, source, target)VALUES(?, ?, ?)",
                movementDto.turn(),
                movementDto.source(),
                movementDto.target());
    }


    public List<MovementDto> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM movement",
                resultSet -> new MovementDto(
                        resultSet.getString("turn"),
                        resultSet.getString("source"),
                        resultSet.getString("target")));
    }

    public void deleteAll() {
        jdbcTemplate.update(
                "DELETE FROM movement");
    }
}
