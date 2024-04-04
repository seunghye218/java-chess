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
        return totalScore - sameFilePawnsDeduction(team);
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
        return board.values().stream()
                .filter(piece -> piece.isSameTeam(team))
                .mapToDouble(Piece::score)
                .sum();
    }

    private double sameFilePawnsDeduction(Team team) {
        return Arrays.stream(File.values())
                .mapToInt(file -> countPawn(team, file))
                .filter(this::existSameFile)
                .sum() * SAME_COLUMN_PAWNS_DEDUCATION;
    }

    private int countPawn(Team team, File file) {
        return (int) Arrays.stream(Rank.values())
                .filter(rank -> Piece.pawnFrom(team).equals(board.get(Point.of(file, rank))))
                .count();
    }

    private boolean existSameFile(int pawnCount) {
        return pawnCount > 1;
    }
}
