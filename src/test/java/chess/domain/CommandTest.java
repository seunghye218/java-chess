package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CommandTest {

    @ParameterizedTest
    @CsvSource(value = {"start, START", "end, END"})
    @DisplayName("명령을 찾을 수 있다.")
    void from(String expression, Command expected) {
        Command actual = Command.from(expression);

        assertThat(actual).isEqualTo(expected);
    }
}
