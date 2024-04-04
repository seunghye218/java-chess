package chess.dao;

import java.util.List;

public interface TurnDao {

    void addTurn(String name);

    void deleteAll();

    List<String> findAll();
}
