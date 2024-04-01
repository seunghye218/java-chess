package chess.dao;

import chess.dto.MovementDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovementDao {

    private static final String SERVER = "localhost:3306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String DB_ERROR_MESSAGE = "애플리케이션을 종료합니다. 관리자에 문의를 주세요.";

    private Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            throw new IllegalStateException("DB 연결 오류가 발생하였습니다. " + DB_ERROR_MESSAGE);
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
            throw new IllegalStateException("기보를 저장할 수 없습니다. " + DB_ERROR_MESSAGE);
        }
    }

    public List<MovementDto> findAll() {
        try (var connection = getConnection()) {
            var statement = connection.prepareStatement("SELECT * FROM movement");
            var resultSet = statement.executeQuery();
            List<MovementDto> movementDtos = new ArrayList<>();
            while (resultSet.next()) {
                movementDtos.add(new MovementDto(
                        resultSet.getString("turn"),
                        resultSet.getString("source"),
                        resultSet.getString("target")));
            }
            return movementDtos;
        } catch (SQLException e) {
            throw new IllegalStateException("기보를 불러올 수 없습니다. " + DB_ERROR_MESSAGE);
        }
    }

    public void deleteAll() {
        try (var connection = getConnection()) {
            var statement = connection.prepareStatement("DELETE FROM movement");
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("게임을 초기화를 실패했습니다. " + DB_ERROR_MESSAGE);
        }
    }
}
