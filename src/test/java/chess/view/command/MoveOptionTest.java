package chess.view.command;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveOptionTest {

    @DisplayName("MoveOption 인자가 2글자가 아니라면 예외를 던진다.")
    @Test
    void invalidCreate() {
        assertThatThrownBy(() -> new MoveOption("a12", "b123"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
