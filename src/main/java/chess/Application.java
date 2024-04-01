package chess;

import chess.controller.ChessController;
import chess.dao.MovementDao;
import chess.service.GameService;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        GameService gameService = new GameService(new MovementDao());
        ChessController controller = new ChessController(inputView, outputView, gameService);

        controller.run();
    }

}
