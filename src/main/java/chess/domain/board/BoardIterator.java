package chess.domain.board;

import chess.domain.point.File;
import chess.domain.point.Point;
import chess.domain.point.Rank;
import java.util.function.Consumer;

public class BoardIterator {

    /*
    * a8 ~ h1까지 순회하는 메서드
     */
    public static void loop(Consumer<Point> consumer) {
        for (int rank = Rank.maxValue(); rank >= Rank.minValue(); rank--) {
            for (char file = File.minValue(); file <= File.maxValue(); file++) {
                Point point = Point.of(File.of(file), Rank.of(rank));
                consumer.accept(point);
            }
        }
    }
}
