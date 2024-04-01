package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.point.File;
import chess.domain.point.Point;
import chess.domain.point.Rank;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class Board {

    private static final double SAME_COLUMN_PAWNS_DEDUCATION = 0.5;
    private final Map<Point, Piece> board;

    Board(Map<Point, Piece> board) {
        this.board = board;
    }

    public Piece move(Point currentPoint, Point destination) {
        Piece currentPiece = board.get(currentPoint);

        board.put(currentPoint, Piece.empty());
        return board.put(destination, currentPiece);
    }

    public boolean isEmpty(Point nextPoint) {
        return board.get(nextPoint) == Piece.empty();
    }

    public Map<Point, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public Piece get(Point point) {
        return board.computeIfAbsent(point, ignore -> {
            throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
        });
    }

    public double score(Team team) {
        if (isKingDead(team)) {
            return 0;
        }
        double totalScore = totalTeamScoreOfDefaultPawn(team);
        totalScore -= sameColumnPawnsDeduction(team);
        return totalScore;
    }

    private boolean isKingDead(Team team) {
        for (Piece piece : board.values()) {
            if (Piece.kingFrom(team).equals(piece)) {
                return false;
            }
        }
        return true;
    }

    private double totalTeamScoreOfDefaultPawn(Team team) {
        double total = 0;

        for (Piece piece : board.values()) {
            if (piece.isSameTeam(team)) {
                total += piece.score();
            }
        }
        return total;
    }

    private double sameColumnPawnsDeduction(Team team) {
        return Arrays.stream(File.values()).mapToInt(file -> countPawnIfSameColumnExistPawns(team, file)).sum()
                * SAME_COLUMN_PAWNS_DEDUCATION;
    }

    private int countPawnIfSameColumnExistPawns(Team team, File file) {
        int count = 0;
        for (Rank rank : Rank.values()) {
            Piece piece = this.board.get(Point.of(file, rank));
            if (Piece.pawnFrom(team).equals(piece)) {
                count++;
            }
        }
        if (count > 1) {
            return count;
        }
        return 0;
    }
}
