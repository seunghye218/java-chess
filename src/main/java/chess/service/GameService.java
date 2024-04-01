package chess.service;

import chess.dao.MovementDao;
import chess.domain.piece.Team;
import chess.dto.MovementDto;
import java.util.List;

public class GameService {

    private final MovementDao movementDao;

    public GameService(MovementDao movementDao) {
        this.movementDao = movementDao;
    }

    public void saveMovement(Team team, String source, String target) {
        movementDao.addMovement(new MovementDto(team.name(), source, target));
    }

    public List<MovementDto> loadMovements() {
        return movementDao.findAll();
    }

    public void deleteAllMovements() {
        movementDao.deleteAll();
    }
}
