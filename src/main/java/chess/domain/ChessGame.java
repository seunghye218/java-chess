package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.player.Player;
import chess.domain.point.Point;
import java.util.HashMap;
import java.util.Map;

public class ChessGame {

    private final Map<Team, Player> players;
    private Team turn;
    private Team winner;

    public ChessGame(Board board) {
        this.players = Map.of(
                Team.WHITE, new Player(Team.WHITE, board),
                Team.BLACK, new Player(Team.BLACK, board));
        this.turn = Team.WHITE;
        this.winner = Team.EMPTY;
    }

    public void currentTurnPlayerMove(Point departure, Point destination) {
        Player player = this.players.get(turn);
        Piece targetPiece = player.move(departure, destination);

        if (Piece.kingFrom(Team.WHITE).equals(targetPiece)) {
            winner = Team.WHITE;
        }
        if (Piece.kingFrom(Team.BLACK).equals(targetPiece)) {
            winner = Team.BLACK;
        }
    }

    public void turnOver() {
        this.turn = turn.opponent();
    }

    public Map<Team, Double> playerScores() {
        Map<Team, Double> scores = new HashMap<>();
        scores.put(Team.WHITE, players.get(Team.WHITE).score());
        scores.put(Team.BLACK, players.get(Team.BLACK).score());
        return scores;
    }

    public boolean isGameOver() {
        return !winner.equals(Team.EMPTY);
    }

    public Team getWinner() {
        return winner;
    }
}
