package chess.view.command;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTest {

    @DisplayName("Command 생성")
    @Test
    void create() {
        assertThatCode(() -> new Command(CommandType.MOVE, List.of("a1", "a2")))
                .doesNotThrowAnyException();
    }
}
