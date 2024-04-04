package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Team;
import chess.domain.point.Point;
import chess.dto.MovementDto;
import chess.service.GameService;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.command.Command;
import chess.view.command.CommandType;
import chess.view.command.MoveOption;
import java.util.List;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private final GameService gameService;

    public ChessController(InputView inputView, OutputView outputView, GameService gameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameService = gameService;
    }

    public void run() {
        try {
            startGame();
            runGame(loadGame());
        } catch (IllegalStateException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private ChessGame loadGame() {
        List<MovementDto> movementDtos = gameService.loadMovements();
        Board board = BoardFactory.createChessBoard(movementDtos);
        Team turn = getTurn(movementDtos);
        return new ChessGame(board, turn);
    }

    private Team getTurn(List<MovementDto> movementDtos) {
        try {
            if (movementDtos.isEmpty()) {
                return Team.WHITE;
            }

            String lastTurn = movementDtos.get(movementDtos.size() - 1).turn();

            return Team.valueOf(lastTurn).opponent();

        } catch (NullPointerException | IndexOutOfBoundsException | IllegalArgumentException e) {
            throw new IllegalStateException("게임을 불러오는 중 오류가 발생했습니다.");
        }
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

    private void runGame(ChessGame game) {
        outputView.printBoardTurn(game.getBoard(), game.currentTurn());
        while (true) {
            try {
                final Command command = new Command(inputView.readCommand());
                final CommandType commandType = command.getCommandType();
                if (CommandType.END == commandType) {
                    outputView.printGameEnd();
                    break;
                }
                if (CommandType.STATUS == commandType) {
                    outputView.printStatus(game.playerStatus());
                }
                if (CommandType.MOVE == commandType) {
                    pieceMoveAndSave(command, game);
                    outputView.printBoardTurn(game.getBoard(), game.currentTurn());
                }

                if (game.isGameOver()) {
                    outputView.printWinner(game.getWinner());
                    gameService.deleteAllMovements();
                    break;
                }
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void pieceMoveAndSave(final Command command, ChessGame game) {
        MoveOption moveOption = new MoveOption(command.options());
        String source = moveOption.source();
        String target = moveOption.target();

        game.currentTurnPlayerMove(Point.of(source), Point.of(target));

        gameService.saveMovement(game.currentTurn(), moveOption.source(), moveOption.target());

        game.turnOver();
    }
}
