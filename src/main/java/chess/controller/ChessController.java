package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Team;
import chess.domain.point.File;
import chess.domain.point.Point;
import chess.domain.point.Rank;
import chess.service.GameService;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private static final String COMMAND_MOVE = "move";
    private static final String COMMAND_END = "end";
    private static final String COMMAND_STATUS = "status";
    private static final int DEPARTURE_INDEX = 1;
    private static final int DESTINATION_INDEX = 2;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final InputView inputView;
    private final OutputView outputView;
    private final GameService gameService;

    public ChessController(InputView inputView, OutputView outputView, GameService gameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameService = gameService;
    }

    public void run() {
        startGame();
        runGame();
    }

    private void startGame() {
        outputView.printGameStart();
        while (true) {
            try {
                inputView.readStart();
                return;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void runGame() {
        Board board = BoardFactory.createInitialChessBoard();
        ChessGame game = new ChessGame(board);
        while (true) {
            try {
                outputView.printBoard(board.getBoard());

                String readCommand = inputView.readCommand();
                if (COMMAND_END.equals(readCommand)) {
                    outputView.printGameEnd();
                    break;
                }
                if (COMMAND_STATUS.equals(readCommand)) {
                    outputView.printStatus(game.playerScores());
                }
                if (readCommand.startsWith(COMMAND_MOVE)) {
                    pieceMove(readCommand, game);
                }

                if (game.isGameOver()) {
                    outputView.printWinner(game.getWinner());
                    gameService.deleteAllMovements();
                    break;
                }
            } catch (IllegalStateException e) {
                outputView.printErrorMessage(e.getMessage());
                return;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void pieceMove(String readCommand, ChessGame game) {
        String[] splitCommands = readCommand.split(" ");
        String source = splitCommands[DEPARTURE_INDEX];
        String target = splitCommands[DESTINATION_INDEX];
        Point departure;
        Point destination;
        try {
            departure = parsePoint(source);
            destination = parsePoint(target);
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("잘못된 위치를 입력하였습니다. 입력값 : " + readCommand);
        }
        game.currentTurnPlayerMove(departure, destination);
        game.turnOver();
        String turn = currentTurn(game);
        gameService.saveMovement(turn, source, target);
    }

    private String currentTurn(ChessGame game) {
        String turn;
        if (Team.WHITE.equals(game.currentTurn())) {
            turn = "white";
        } else {
            turn = "black";
        }
        return turn;
    }

    private Point parsePoint(String splitCommand) {
        File file = File.of(splitCommand.charAt(FILE_INDEX));
        Rank rank = Rank.of(Integer.parseInt(String.valueOf(splitCommand.charAt(RANK_INDEX))));
        return Point.of(file, rank);
    }
}
