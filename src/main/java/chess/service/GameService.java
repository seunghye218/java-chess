package chess.service;

import chess.dao.MovementDao;
import chess.dto.MovementDto;

public class GameService {

    private final MovementDao movementDao;

    public GameService() {
        this.movementDao = new MovementDao();
    }

    public void saveMovement(String turn, String source, String target) {
        movementDao.addMovement(new MovementDto(turn, source, target));
    }

    public void deleteAllMovements() {
        movementDao.deleteAll();
    }
}
