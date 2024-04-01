package chess.domain.piece;

import chess.domain.point.Point;
import java.util.EnumMap;

public final class Bishop extends Piece {

    private static final EnumMap<Team, Bishop> POOL;

    static {
        POOL = new EnumMap<>(Team.class);
        POOL.put(Team.WHITE, new Bishop(Team.WHITE));
        POOL.put(Team.BLACK, new Bishop(Team.BLACK));
    }

    private Bishop(Team team) {
        super(team);
    }

    static Bishop from(Team team) {
        return POOL.get(team);
    }

    @Override
    public boolean isMovable(Point currentPoint, Point nextPoint, Piece target) {
        return currentPoint.isSlopeOneDiagonal(nextPoint);
    }

    @Override
    public double score() {
        return 3;
    }
}
