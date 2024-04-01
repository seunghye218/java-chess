package chess.service;

import chess.dao.MovementDao;
import chess.domain.piece.Team;
import chess.dto.MovementDto;
import java.util.List;

public class GameService {

    private final MovementDao movementDao;

    public GameService() {
        this.movementDao = new MovementDao();
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
