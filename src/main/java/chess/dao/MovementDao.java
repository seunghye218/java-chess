package chess.dao;

import chess.dto.MovementDto;
import java.util.List;

public interface MovementDao {

    void addMovement(MovementDto movementDto);


    List<MovementDto> findAll();

    void deleteAll();
}
