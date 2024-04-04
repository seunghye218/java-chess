package chess.service;

import chess.dao.ChessBoardDao;
import chess.dao.TurnDao;
import chess.domain.board.BoardIterator;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.point.Point;
import chess.view.PieceCharacters;
import java.util.List;
import java.util.Map;

public class GameService {

    private static final String INITIAL_TURN = "WHITE";
    private static final String INITIAL_BOARD = "RNBQKBNRPPPPPPPP................................pppppppprnbqkbnr";

    private final ChessBoardDao chessBoardDao;
    private final TurnDao turnDao;

    public GameService(ChessBoardDao chessBoardDao, TurnDao turnDao) {
        this.chessBoardDao = chessBoardDao;
        this.turnDao = turnDao;
    }

    public void saveChessBoard(ChessGame game) {
        chessBoardDao.deleteAll();
        Map<Point, Piece> board = game.getBoard().getBoard();
        StringBuilder rawBoard = new StringBuilder();

        BoardIterator.loop((point) -> {
            Piece piece = board.get(point);
            rawBoard.append(PieceCharacters.characterFrom(piece));
        });

        chessBoardDao.addChessBoard(rawBoard.toString());
    }


    public void saveTurn(Team team) {
        turnDao.deleteAll();
        turnDao.addTurn(team.name());
    }

    public String loadTurn() {
        List<String> allTurn = turnDao.findAll();

        if (allTurn.isEmpty()) {
            return INITIAL_TURN;
        }

        return allTurn.get(0);
    }

    public String loadChessBoard() {
        List<String> allBoard = chessBoardDao.findAll();

        if (allBoard.isEmpty()) {
            return INITIAL_BOARD;
        }

        return allBoard.get(0);
    }

    public void deleteAll() {
        chessBoardDao.deleteAll();
        turnDao.deleteAll();
    }
}
