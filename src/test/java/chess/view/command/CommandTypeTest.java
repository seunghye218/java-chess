package chess.view.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTypeTest {

    @DisplayName("CommandType 객체 생성 테스트")
    @Test
    void create() {
        assertAll(
            () -> assertEquals(CommandType.START, CommandType.of("start")),
            () -> assertEquals(CommandType.END, CommandType.of("end")),
            () -> assertEquals(CommandType.STATUS, CommandType.of("status")),
            () -> assertEquals(CommandType.MOVE, CommandType.of("move"))
        );
    }

    @DisplayName("CommandType 객체 생성 실패 테스트")
    @Test
    void invalidCreate() {
        assertThrows(IllegalArgumentException.class, () -> CommandType.of("invalid"));
    }
}
