package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Team;
import chess.domain.point.Point;
import chess.service.GameService;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.command.Command;
import chess.view.command.CommandType;
import chess.view.command.MoveOption;
import java.util.EnumMap;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private final GameService gameService;
    private final EnumMap<CommandType, CommandExecute> commandExecutor;

    public ChessController(InputView inputView, OutputView outputView, GameService gameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameService = gameService;
        this.commandExecutor = createCommandExecutor();
    }

    private EnumMap<CommandType, CommandExecute> createCommandExecutor() {
        EnumMap<CommandType, CommandExecute> commandExecutor = new EnumMap<>(CommandType.class);
        commandExecutor.put(CommandType.MOVE, this::move);
        commandExecutor.put(CommandType.STATUS, this::status);
        commandExecutor.put(CommandType.END, this::end);
        return commandExecutor;
    }

    public void run() {
        try {
            Command command = startGame();
            runGame(loadGame(), command);
        } catch (IllegalStateException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private Command startGame() {
        outputView.printGameStart();
        while (true) {
            try {
                return new Command(inputView.readStart());
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private ChessGame loadGame() {
        Board board = BoardFactory.createChessBoard(gameService.loadChessBoard());
        Team turn = Team.valueOf(gameService.loadTurn());
        return new ChessGame(board, turn);
    }

    private void runGame(ChessGame game, Command command) {
        outputView.printBoardTurn(game.getBoard(), game.currentTurn());
        while (!game.isGameOver() && !command.isEnd()) {
            try {
                command = new Command(inputView.readCommand());
                CommandExecute commandExecute = commandExecutor.get(command.type());

                commandExecute.execute(command, game);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
        gameOver(game);
    }

    private void status(Command command, ChessGame game) {
        outputView.printStatus(game.playerStatus());
    }

    private void end(Command command, ChessGame game) {
        gameService.saveChessBoard(game);
        gameService.saveTurn(game.currentTurn());
        outputView.printGameEnd();
    }

    private void move(Command command, ChessGame game) {
        pieceMove(command, game);
        outputView.printBoardTurn(game.getBoard(), game.currentTurn());
    }

    private void pieceMove(final Command command, ChessGame game) {
        MoveOption moveOption = new MoveOption(command.options());
        String source = moveOption.source();
        String target = moveOption.target();

        game.currentTurnPlayerMove(Point.of(source), Point.of(target));
    }

    private void gameOver(ChessGame game) {
        if (game.isGameOver()) {
            outputView.printWinner(game.getWinner());
            gameService.deleteAll();
        }
    }

    @FunctionalInterface
    interface CommandExecute {

        void execute(Command command, ChessGame game);
    }
}
