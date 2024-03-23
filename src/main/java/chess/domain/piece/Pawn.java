package chess.domain.piece;

import chess.domain.Point;
import java.util.ArrayList;
import java.util.List;


public final class Pawn extends Piece {

    private static final String NAME = "P";
    private static final int TWO_RANK = 2;

    Pawn(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMovable(Point currentPoint, Point nextPoint) {
        Team team = getTeam();
        List<Point> movablePoints = findMovablePoints(currentPoint, team);

        return movablePoints.contains(nextPoint);
    }

    private List<Point> findMovablePoints(Point currentPoint, Team team) {
        List<Point> points = new ArrayList<>();
        int forwardDirection = team.forwardDirection();

        if (currentPoint.isInitialPointOfPawn()) {
            findMovablePoint(points, currentPoint, 0, TWO_RANK * forwardDirection);
        }
        for (Direction direction : Direction.findPawnDirections()) {
            findMovablePoint(points, currentPoint, direction.file(), direction.rank() * forwardDirection);
        }
        return points;
    }

    private void findMovablePoint(List<Point> points, Point currentPoint, int addFile, int addRank) {
        if (currentPoint.addable(addFile, addRank)) {
            Point point = currentPoint.add(addFile, addRank);
            points.add(point);
        }
    }
}
