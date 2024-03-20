package chess.domain.piece;

import java.util.List;

public enum Direction {

    UPPER(0, 1),
    LOWER(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),

    UPPER_LEFT(-1, 1),
    UPPER_RIGHT(1, 1),
    LOWER_LEFT(-1, -1),
    LOWER_RIGHT(1, -1),

    KNIGHT_UPPER_LEFT(-1, 2),
    KNIGHT_UPPER_RIGHT(1, 2),
    KNIGHT_LOWER_LEFT(-1, -2),
    KNIGHT_LOWER_RIGHT(1, -2),
    KNIGHT_LEFT_UPPER(-2, 1),
    KNIGHT_LEFT_LOWER(-2, -1),
    KNIGHT_RIGHT_UPPER(2, 1),
    KNIGHT_RIGHT_LOWER(2, -1);

    private final int directionOfFile;
    private final int directionOfRank;

    Direction(int directionOfFile, int directionOfRank) {
        this.directionOfFile = directionOfFile;
        this.directionOfRank = directionOfRank;
    }

    public static List<Direction> findKnightDirections() {
        return List.of(
                KNIGHT_UPPER_LEFT,
                KNIGHT_UPPER_RIGHT,
                KNIGHT_LOWER_LEFT,
                KNIGHT_LOWER_RIGHT,
                KNIGHT_LEFT_UPPER,
                KNIGHT_LEFT_LOWER,
                KNIGHT_RIGHT_UPPER,
                KNIGHT_RIGHT_LOWER
        );
    }

    public static List<Direction> findPawnDirections() {
        return List.of(UPPER_LEFT, UPPER, UPPER_RIGHT);
    }

    public int rank() {
        return directionOfRank;
    }

    public int file() {
        return directionOfFile;
    }
}
