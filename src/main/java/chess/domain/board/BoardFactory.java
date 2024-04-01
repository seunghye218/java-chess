package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.point.File;
import chess.domain.point.Point;
import chess.domain.point.Rank;
import chess.dto.MovementDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardFactory {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    public static Board createChessBoard(List<MovementDto> movementDtos) {
        Board board = createInitialChessBoard();

        if (movementDtos.isEmpty()) {
            return board;
        }

        for (MovementDto movementDto : movementDtos) {
            Point source = Point.of(File.of(movementDto.source().charAt(FILE_INDEX)),
                    Rank.of(Integer.parseInt(String.valueOf(movementDto.source().charAt(RANK_INDEX)))));
            Point target = Point.of(File.of(movementDto.target().charAt(FILE_INDEX)),
                    Rank.of(Integer.parseInt(String.valueOf(movementDto.target().charAt(RANK_INDEX)))));
            board.move(source, target);
        }
        return board;
    }

    public static Board createInitialChessBoard() {
        Map<Point, Piece> board = new HashMap<>();

        board.putAll(WhiteFirstLine());
        board.putAll(WhiteSecondLine());
        board.putAll(putEmptyLines(3, 4));
        board.putAll(BlackSecondLine());
        board.putAll(BlackFirstLine());
        return new Board(board);
    }

    public static Board createCustumBoard(Map<Point, Piece> pieces) {
        Map<Point, Piece> board = new HashMap<>();

        board.putAll(putEmptyLines(1, 8));
        board.putAll(pieces);

        return new Board(board);
    }

    private static Map<Point, Piece> putEmptyLines(int beginRank, int size) {
        Map<Point, Piece> emptyLines = new HashMap<>();

        for (int rank = beginRank; rank < beginRank + size; rank++) {
            emptyLines.putAll(EmptyLine(rank));
        }

        return emptyLines;
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
