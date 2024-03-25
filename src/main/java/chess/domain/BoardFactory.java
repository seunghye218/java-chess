package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.point.File;
import chess.domain.point.Point;
import chess.domain.point.Rank;
import java.util.HashMap;
import java.util.Map;

public class BoardFactory {

    public static Map<Point, Piece> createInitialChessBoard() {
        Map<Point, Piece> board = new HashMap<>();

        board.putAll(WhiteFirstLine());
        board.putAll(WhiteSecondLine());
        putEmptyLines(3, 4, board);
        board.putAll(BlackSecondLine());
        board.putAll(BlackFirstLine());
        return board;
    }

    public static Map<Point, Piece> createEmptyBoard() {
        Map<Point, Piece> board = new HashMap<>();

        putEmptyLines(1, 8, board);

        return board;
    }

    private static void putEmptyLines(int beginRank, int size, Map<Point, Piece> board) {
        for (int rank = beginRank; rank < beginRank + size; rank++) {
            board.putAll(EmptyLine(rank));
        }
    }

    private static Map<Point, Piece> BlackFirstLine() {
        return teamFirstLine(8, Team.BLACK);
    }

    private static Map<Point, Piece> BlackSecondLine() {
        return teamSecondLine(7, Team.BLACK);
    }

    private static Map<Point, Piece> WhiteSecondLine() {
        return teamSecondLine(2, Team.WHITE);
    }

    private static Map<Point, Piece> WhiteFirstLine() {
        return teamFirstLine(1, Team.WHITE);
    }

    private static Map<Point, Piece> teamSecondLine(int rank, Team team) {
        Map<Point, Piece> line = new HashMap<>();

        for (char c = File.minValue(); c <= File.maxValue(); c++) {
            line.put(Point.of(File.of(c), Rank.of(rank)), Piece.pawnFrom(team));
        }
        return line;
    }

    private static Map<Point, Piece> teamFirstLine(int rank, Team team) {
        return Map.of(
                Point.of(File.A, Rank.of(rank)), Piece.rookFrom(team),
                Point.of(File.B, Rank.of(rank)), Piece.knightFrom(team),
                Point.of(File.C, Rank.of(rank)), Piece.bishopFrom(team),
                Point.of(File.D, Rank.of(rank)), Piece.queenFrom(team),
                Point.of(File.E, Rank.of(rank)), Piece.kingFrom(team),
                Point.of(File.F, Rank.of(rank)), Piece.bishopFrom(team),
                Point.of(File.G, Rank.of(rank)), Piece.knightFrom(team),
                Point.of(File.H, Rank.of(rank)), Piece.rookFrom(team)
        );
    }

    private static Map<Point, Piece> EmptyLine(int rank) {
        Map<Point, Piece> line = new HashMap<>();

        for (char c = File.minValue(); c <= File.maxValue(); c++) {
            line.put(Point.of(File.of(c), Rank.of(rank)), Piece.empty());
        }
        return line;
    }
}
