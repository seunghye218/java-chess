package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.player.Players;
import chess.domain.point.Point;

public class ChessGame {

    private final Board board;
    private final Players players;
    private Team winner;

    public ChessGame(Board board, Team turn) {
        this.players = new Players(board, turn);
        this.board = board;
        this.winner = Team.EMPTY;
    }

    public void currentTurnPlayerMove(Point departure, Point destination) {
        Piece targetPiece = players.move(departure, destination);

        if (Piece.kingFrom(Team.WHITE).equals(targetPiece)) {
            winner = Team.WHITE;
        }
        if (Piece.kingFrom(Team.BLACK).equals(targetPiece)) {
            winner = Team.BLACK;
        }

        players.turnOver();
    }

    public Status playerStatus() {
        double whiteScore = players.whiteScore();
        double blackScore = players.blackScore();

        return new Status(whiteScore, blackScore);
    }

    public boolean isGameOver() {
        return !winner.equals(Team.EMPTY);
    }

    public Team getWinner() {
        return winner;
    }

    public Team currentTurn() {
        return players.currentTurn();
    }

    public Board getBoard() {
        return board;
    }
}
