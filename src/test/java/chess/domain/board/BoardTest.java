package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.point.File;
import chess.domain.point.Point;
import chess.domain.point.Rank;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @DisplayName("기물을 a1에서 a3으로 이동 시킬 수 있다.")
    @Test
    void move1() {
        Board board = BoardFactory.createCustumBoard(Map.of(
                Point.of(File.A, Rank.FIRST), Piece.bishopFrom(Team.WHITE)
        ));

        board.move(
                Point.of(File.A, Rank.FIRST),
                Point.of(File.A, Rank.THIRD));

        assertThat(board.get(Point.of(File.A, Rank.THIRD))).isEqualTo(Piece.bishopFrom(Team.WHITE));
    }

    @DisplayName("기물을 a1 에서 a3 으로 이동 시키면 a1 은 비어있다.")
    @Test
    void move2() {
        Board board = BoardFactory.createCustumBoard(Map.of(
                Point.of(File.A, Rank.FIRST), Piece.bishopFrom(Team.WHITE)
        ));

        board.move(
                Point.of(File.A, Rank.FIRST),
                Point.of(File.A, Rank.THIRD));

        assertThat(board.get(Point.of(File.A, Rank.FIRST))).isEqualTo(Piece.empty());
    }

    @DisplayName("입력한 진영의 모든 폰이 세로로 있지 않은 경우 남아있는 기물들의 점수를 얻는다.")
    @Test
    void score1() {
        Team team = Team.BLACK;
        Board board = BoardFactory.createCustumBoard(Map.of(
                Point.of(File.A, Rank.THIRD), Piece.kingFrom(team),
                Point.of(File.B, Rank.THIRD), Piece.queenFrom(team),
                Point.of(File.C, Rank.THIRD), Piece.rookFrom(team),
                Point.of(File.D, Rank.THIRD), Piece.bishopFrom(team),
                Point.of(File.E, Rank.THIRD), Piece.pawnFrom(team),
                Point.of(File.F, Rank.THIRD), Piece.pawnFrom(team),
                Point.of(File.G, Rank.THIRD), Piece.pawnFrom(team)));

        double score = board.score(team);

        assertThat(score).isEqualTo(20);
    }

    @DisplayName("입력한 진영의 폰이 세로로 있는 경우 남아있는 기물들의 점수를 얻는다.")
    @Test
    void score2() {
        Team team = Team.WHITE;
        Board board = BoardFactory.createCustumBoard(Map.of(
                Point.of(File.A, Rank.THIRD), Piece.kingFrom(team),
                Point.of(File.B, Rank.THIRD), Piece.rookFrom(team),
                Point.of(File.G, Rank.THIRD), Piece.knightFrom(team),
                Point.of(File.D, Rank.THIRD), Piece.pawnFrom(team),
                Point.of(File.E, Rank.THIRD), Piece.pawnFrom(team),
                Point.of(File.F, Rank.THIRD), Piece.pawnFrom(team),
                Point.of(File.F, Rank.FOURTH), Piece.pawnFrom(team),
                Point.of(File.H, Rank.THIRD), Piece.queenFrom(team)));

        double score = board.score(team);

        assertThat(score).isEqualTo(19.5);
    }

    @DisplayName("입력한 진영에 킹이 없다면 0점이다.")
    @Test
    void score3() {
        Team team = Team.WHITE;
        Board board = BoardFactory.createCustumBoard(Map.of(
                Point.of(File.A, Rank.THIRD), Piece.queenFrom(team),
                Point.of(File.B, Rank.THIRD), Piece.rookFrom(team),
                Point.of(File.G, Rank.THIRD), Piece.knightFrom(team),
                Point.of(File.D, Rank.THIRD), Piece.pawnFrom(team),
                Point.of(File.E, Rank.THIRD), Piece.pawnFrom(team),
                Point.of(File.F, Rank.THIRD), Piece.pawnFrom(team),
                Point.of(File.F, Rank.FOURTH), Piece.pawnFrom(team)));

        double score = board.score(team);

        assertThat(score).isEqualTo(0);
    }

    @DisplayName("입력된 팀의 기물의 점수만 계산한다.")
    @Test
    void score4() {
        Team whiteTeam = Team.WHITE;
        Board board = BoardFactory.createCustumBoard(Map.of(
                Point.of(File.A, Rank.THIRD), Piece.kingFrom(whiteTeam),
                Point.of(File.B, Rank.THIRD), Piece.rookFrom(whiteTeam),
                Point.of(File.G, Rank.THIRD), Piece.knightFrom(whiteTeam),
                Point.of(File.D, Rank.THIRD), Piece.pawnFrom(whiteTeam),
                Point.of(File.E, Rank.THIRD), Piece.pawnFrom(whiteTeam),
                Point.of(File.F, Rank.THIRD), Piece.pawnFrom(whiteTeam),
                Point.of(File.F, Rank.FOURTH), Piece.pawnFrom(whiteTeam),
                Point.of(File.H, Rank.THIRD), Piece.queenFrom(whiteTeam),
                Point.of(File.A, Rank.EIGHTH), Piece.kingFrom(Team.BLACK),
                Point.of(File.H, Rank.EIGHTH), Piece.queenFrom(Team.BLACK)));

        double blackScore = board.score(Team.BLACK);

        assertThat(blackScore).isEqualTo(9);
    }
}
