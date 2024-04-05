package chess.domain.player;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.point.Point;
import java.util.EnumMap;

public class Players {

    private final EnumMap<Team, Player> players;
    private Team turn;

    public Players(Board board, Team turn) {
        this.players = new EnumMap<>(Team.class);
        this.players.put(Team.WHITE, new Player(Team.WHITE, board));
        this.players.put(Team.BLACK, new Player(Team.BLACK, board));
        this.turn = turn;
    }

    public Piece move(Point departure, Point destination) {
        Player currentplayer = players.get(turn);
        return currentplayer.move(departure, destination);
    }

    public void turnOver() {
        turn = turn.opponent();
    }

    public double whiteScore() {
        Player player = players.get(Team.WHITE);
        return player.score();
    }

    public double blackScore() {
        Player player = players.get(Team.BLACK);
        return player.score();
    }

    public Team currentTurn() {
        return turn;
    }
}
