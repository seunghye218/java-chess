package chess.service;

import chess.dao.MovementDao;
import chess.dto.MovementDto;
import java.util.List;

public class GameService {

    private final MovementDao movementDao;

    public GameService() {
        this.movementDao = new MovementDao();
    }

    public void saveMovement(String turn, String source, String target) {
        movementDao.addMovement(new MovementDto(turn, source, target));
    }

    public List<MovementDto> loadMovements() {
        return movementDao.findAll();
    }

    public void deleteAllMovements() {
        movementDao.deleteAll();
    }
}
