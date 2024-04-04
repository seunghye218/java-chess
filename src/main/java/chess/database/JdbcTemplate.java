package chess.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    private static final String APP_EXIT_CONTACT_ADMIN_MESSAGE = "애플리케이션을 종료합니다. 관리자에 문의를 주세요.";

    private final DBConnection dbConnection;

    public JdbcTemplate(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void update(String sql, String... params) {
        try (var connection = dbConnection.getConnection()) {
            var statement = connection.prepareStatement(sql);
            setParameters(params, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("기보를 업데이트할 수 없습니다. " + APP_EXIT_CONTACT_ADMIN_MESSAGE);
        }
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper, String... params) {
        try (var connection = dbConnection.getConnection()) {
            var statement = connection.prepareStatement(sql);
            setParameters(params, statement);
            var resultSet = statement.executeQuery();

            List<T> results = new ArrayList<>();
            while (resultSet.next()) {
                results.add(rowMapper.mapRow(resultSet));
            }
            return results;
        } catch (SQLException e) {
            throw new IllegalStateException("기보를 불러올 수 없습니다. " + APP_EXIT_CONTACT_ADMIN_MESSAGE);
        }
    }

    private void setParameters(String[] params, PreparedStatement statement) throws SQLException {
        for (int i = 0; i < params.length; ++i) {
            statement.setString(i + 1, params[i]);
        }
    }
}
