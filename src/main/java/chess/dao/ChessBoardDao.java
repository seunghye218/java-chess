package chess.dao;

import java.util.List;

public interface ChessBoardDao {

    void addChessBoard(String string);

    List<String> findAll();

    void deleteAll();
}
