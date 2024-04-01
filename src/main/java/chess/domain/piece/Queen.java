package chess.domain.piece;

import chess.domain.point.Point;
import java.util.EnumMap;

public final class Queen extends Piece {

    private static final EnumMap<Team, Queen> POOL;

    static {
        POOL = new EnumMap<>(Team.class);
        POOL.put(Team.WHITE, new Queen(Team.WHITE));
        POOL.put(Team.BLACK, new Queen(Team.BLACK));
    }

    private Queen(Team team) {
        super(team);
    }

    static Queen from(Team team) {
        return POOL.get(team);
    }

    @Override
    public boolean isMovable(Point currentPoint, Point nextPoint, Piece target) {
        return currentPoint.isStraight(nextPoint) || currentPoint.isSlopeOneDiagonal(nextPoint);
    }

    @Override
    public double score() {
        return 9;
    }
}
