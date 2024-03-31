package chess.dao;

import chess.dto.MovementDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MovementDao {

    private static final String SERVER = "localhost:3306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void addMovement(MovementDto movementDto) {
        try (var connection = getConnection()) {
            var statement = connection.prepareStatement("INSERT INTO movement(turn, source, target)VALUES(?, ?, ?)");
            statement.setString(1, movementDto.turn());
            statement.setString(2, movementDto.source());
            statement.setString(3, movementDto.target());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("게임을 진행할 수 없는 상태입니다. 게임을 종료합니다.");
        }
    }

    public void deleteAll() {
        try (var connection = getConnection()) {
            var statement = connection.prepareStatement("DELETE FROM movement");
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
