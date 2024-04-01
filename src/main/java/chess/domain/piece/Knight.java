package chess.domain.piece;

import chess.domain.point.Point;
import java.util.EnumMap;

public final class Knight extends Piece {

    private static final EnumMap<Team, Knight> POOL;

    static {
        POOL = new EnumMap<>(Team.class);
        POOL.put(Team.WHITE, new Knight(Team.WHITE));
        POOL.put(Team.BLACK, new Knight(Team.BLACK));
    }

    private Knight(Team team) {
        super(team);
    }

    static Knight from(Team team) {
        return POOL.get(team);
    }

    @Override
    public boolean isMovable(Point currentPoint, Point nextPoint, Piece target) {
        return Math.abs(currentPoint.multiplyAxis(nextPoint)) == 2;
    }

    @Override
    public double score() {
        return 2.5;
    }
}
