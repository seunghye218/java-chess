package chess.view.command;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveOptionTest {

    @DisplayName("MoveOption 각 인자가 2 글자가 아니라면 예외를 던진다.")
    @Test
    void invalidCreate1() {
        assertThatThrownBy(() -> new MoveOption(List.of("a12", "b123")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("각 위치는 두글자 입니다. ex) 'a1', 'h8'");
    }

    @DisplayName("MoveOption 인자가 1 개 라면 예외를 던진다.")
    @Test
    void invalidCreate2() {
        assertThatThrownBy(() -> new MoveOption(List.of("a1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("move 명령은 두 개의 위치를 입력해야 합니다.");
    }
}
