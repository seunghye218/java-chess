package chess.domain.piece;

import chess.domain.point.Point;
import java.util.EnumMap;

public final class King extends Piece {

    private static final EnumMap<Team, King> POOL;

    static {
        POOL = new EnumMap<>(Team.class);
        POOL.put(Team.WHITE, new King(Team.WHITE));
        POOL.put(Team.BLACK, new King(Team.BLACK));
    }

    private King(Team team) {
        super(team);
    }

    static King from(Team team) {
        return POOL.get(team);
    }

    @Override
    public boolean isMovable(Point currentPoint, Point nextPoint, Piece target) {
        return currentPoint.isAround(nextPoint);
    }

    @Override
    public double score() {
        return 0;
    }
}
