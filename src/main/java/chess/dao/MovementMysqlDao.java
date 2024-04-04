package chess.dao;

import chess.database.JdbcTemplate;
import chess.dto.MovementDto;
import java.util.List;

public class MovementMysqlDao implements MovementDao {

        private final JdbcTemplate jdbcTemplate;

        public MovementMysqlDao(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        public void addMovement(MovementDto movementDto) {
            jdbcTemplate.update(
                    "INSERT INTO movement(source, target)VALUES(?, ?)",
                    movementDto.source(),
                    movementDto.target());
        }


        public List<MovementDto> findAll() {
            return jdbcTemplate.query(
                    "SELECT * FROM movement",
                    resultSet -> new MovementDto(
                            resultSet.getString("source"),
                            resultSet.getString("target")));
        }

        public void deleteAll() {
            jdbcTemplate.update(
                    "DELETE FROM movement");
        }

}
