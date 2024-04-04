package chess.view;

import chess.domain.Status;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.point.File;
import chess.domain.point.Point;
import chess.domain.point.Rank;

public class OutputView {

    private static final String ERROR_SUFFIX = "[ERROR]";
    private static final String TEAM_WHITE = "White";
    private static final String TEAM_BLACK = "Black";

    public void printBoard(Board board) {
        StringBuilder builder = new StringBuilder();

        for (int rank = Rank.maxValue(); rank >= Rank.minValue(); rank--) {
            for (char file = File.minValue(); file <= File.maxValue(); file++) {
                Piece piece = board.get(Point.of(File.of(file), Rank.of(rank)));
                builder.append(PieceCharacters.characterFrom(piece));
            }
            builder.append(System.lineSeparator());
        }

        System.out.println(builder);
    }

    public void printGameStart() {
        System.out.println("""
                > 체스 게임을 시작합니다.
                > 게임 시작 : start
                > 게임 종료 : end
                > 게임 이동 : move source위치 target위치 - 예. move b2 b3""");
    }

    public void printErrorMessage(String errorMessage) {
        System.out.printf("%s %s%n", ERROR_SUFFIX, errorMessage);
    }

    public void printGameEnd() {
        System.out.println("게임이 종료되었습니다.");
    }

    public void printStatus(Status status) {
        StringBuilder builder = new StringBuilder();

        double whiteScore = status.whiteScore();
        double blackScore = status.blackScore();
        builder.append(String.format("%s 진영 점수: ", TEAM_WHITE))
                .append(whiteScore)
                .append(System.lineSeparator())
                .append(String.format("%s 진영 점수: ", TEAM_BLACK))
                .append(blackScore)
                .append(System.lineSeparator());

        if (whiteScore == blackScore) {
            builder.append("동점 입니다.");
        }
        if (whiteScore > blackScore) {
            builder.append(String.format("%s 진영이 이기고 있습니다.", TEAM_WHITE));
        }
        if (whiteScore < blackScore) {
            builder.append(String.format("%s 진영이 이기고 있습니다.", TEAM_BLACK));
        }

        System.out.println(builder.append(System.lineSeparator()));
    }

    public void printWinner(Team winner) {
        StringBuilder builder = new StringBuilder("체크메이트!");

        builder.append(System.lineSeparator());
        if (Team.WHITE.equals(winner)) {
            builder.append(String.format("%s 진영이 이겼습니다.", TEAM_WHITE));
        }
        if (Team.BLACK.equals(winner)) {
            builder.append(String.format("%s 진영이 이겼습니다.", TEAM_BLACK));
        }
        if (Team.EMPTY.equals(winner)) {
            builder.append("아직 승자가 없습니다.");
        }
        builder.append(System.lineSeparator());
        builder.append("게임을 종료합니다.");

        System.out.println(builder);
    }
}
