package chess.view;

import chess.domain.board.BoardIterator;
import chess.domain.Status;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.point.Rank;

public class OutputView {

    private static final String ERROR_SUFFIX = "[ERROR]";

    public void printGameStart() {
        System.out.println("""
                > 체스 게임을 시작합니다.
                > 게임 시작 : start
                > 게임 종료 : end
                > 게임 이동 : move source위치 target위치 - 예. move b2 b3""");
    }

    public void printBoardTurn(Board board, Team team) {
        printBoard(board);
        printCurrentTurnPlayer(team);
    }

    private void printBoard(Board board) {
        StringBuilder builder = new StringBuilder();

        BoardIterator.loop((point) -> {
            Piece piece = board.get(point);
            builder.append(PieceCharacters.characterFrom(piece));
        });
        builder.append(System.lineSeparator());

        for (int i = Rank.maxValue() ; i >= Rank.minValue() ; i--) {
            builder.insert(8 * i, System.lineSeparator());
        }
        System.out.print(builder);
    }

    private void printCurrentTurnPlayer(Team team) {
        System.out.println(team.name() + " 턴입니다." + System.lineSeparator());
    }

    public void printGameEnd() {
        System.out.println("게임이 종료되었습니다.");
    }

    public void printStatus(Status status) {
        StringBuilder builder = new StringBuilder();

        double whiteScore = status.whiteScore();
        double blackScore = status.blackScore();
        builder.append(String.format("%s 진영 점수: ", Team.WHITE.name()))
                .append(whiteScore)
                .append(System.lineSeparator())
                .append(String.format("%s 진영 점수: ", Team.BLACK.name()))
                .append(blackScore)
                .append(System.lineSeparator());

        if (whiteScore == blackScore) {
            builder.append("동점 입니다.");
        }
        if (whiteScore > blackScore) {
            builder.append(String.format("%s 진영이 이기고 있습니다.", Team.WHITE.name()));
        }
        if (whiteScore < blackScore) {
            builder.append(String.format("%s 진영이 이기고 있습니다.", Team.BLACK.name()));
        }

        System.out.println(builder.append(System.lineSeparator()));
    }

    public void printWinner(Team winner) {
        StringBuilder builder = new StringBuilder("체크메이트!");

        builder.append(System.lineSeparator());
        if (Team.WHITE.equals(winner)) {
            builder.append(String.format("%s 진영이 이겼습니다.", Team.BLACK.name()));
        }
        if (Team.BLACK.equals(winner)) {
            builder.append(String.format("%s 진영이 이겼습니다.", Team.WHITE.name()));
        }
        if (Team.EMPTY.equals(winner)) {
            builder.append("아직 승자가 없습니다.");
        }
        builder.append(System.lineSeparator());
        builder.append("게임을 종료합니다.");

        System.out.println(builder);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.printf("%s %s%n", ERROR_SUFFIX, errorMessage);
    }
}
