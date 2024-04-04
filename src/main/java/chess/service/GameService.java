package chess.service;

import chess.dao.ChessBoardDao;
import chess.dao.MovementDao;
import chess.dao.TurnDao;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.point.File;
import chess.domain.point.Point;
import chess.domain.point.Rank;
import chess.dto.MovementDto;
import chess.view.PieceCharacters;
import java.util.List;
import java.util.Map;

public class GameService {

    private final MovementDao movementDao;
    private final ChessBoardDao chessBoardDao;
    private final TurnDao turnDao;

    public GameService(MovementDao movementDao, ChessBoardDao chessBoardDao, TurnDao turnDao) {
        this.movementDao = movementDao;
        this.chessBoardDao = chessBoardDao;
        this.turnDao = turnDao;
    }

    public void saveMovement(String source, String target) {
        movementDao.addMovement(new MovementDto(source, target));
    }

    public List<MovementDto> loadMovements() {
        return movementDao.findAll();
    }

    public void deleteAllMovements() {
        movementDao.deleteAll();
    }

    public void saveChessBoard(ChessGame game) {
        Map<Point, Piece> board = game.getBoard().getBoard();
        StringBuilder rawBoard = new StringBuilder();

        // TODO 출력뷰 중복 로직 제거
        for (int rank = Rank.maxValue(); rank >= Rank.minValue(); rank--) {
            for (char file = File.minValue(); file <= File.maxValue(); file++) {
                Piece piece = board.get(Point.of(File.of(file), Rank.of(rank)));
                rawBoard.append(PieceCharacters.characterFrom(piece));
            };
        }

        chessBoardDao.addChessBoard(rawBoard.toString());
    }

    public void saveTurn(Team team) {
        turnDao.deleteAll();
        turnDao.addTurn(team.name());
    }
}
