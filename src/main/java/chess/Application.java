package chess;

import chess.controller.ChessController;
import chess.dao.ChessBoardMysqlDao;
import chess.dao.TurnMysqlDao;
import chess.database.DBConnection;
import chess.database.JdbcTemplate;
import chess.service.GameService;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        GameService gameService = getGameService();
        ChessController controller = new ChessController(inputView, outputView, gameService);

        controller.run();
    }

    private static GameService getGameService() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(new DBConnection());
        return new GameService(
                new ChessBoardMysqlDao(jdbcTemplate),
                new TurnMysqlDao(jdbcTemplate));
    }
}
