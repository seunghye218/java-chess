package chess.domain.piece;

import chess.domain.point.Point;
import java.util.EnumMap;

public final class Rook extends Piece {

    private static final EnumMap<Team, Rook> POOL;

    static {
        POOL = new EnumMap<>(Team.class);
        POOL.put(Team.WHITE, new Rook(Team.WHITE));
        POOL.put(Team.BLACK, new Rook(Team.BLACK));
    }

    private Rook(Team team) {
        super(team);
    }

    static Rook from(Team team) {
        return POOL.get(team);
    }

    @Override
    public boolean isMovable(Point currentPoint, Point nextPoint, Piece target) {
        return currentPoint.isStraight(nextPoint);
    }

    @Override
    public double score() {
        return 5;
    }
}
