package chess.view.command;

import chess.domain.point.File;
import java.util.List;

public record MoveOption(String source, String target) {

    private static final int SOURCE_INDEX = 0;
    private static final int TARGET_INDEX = 1;

    public MoveOption {
        if (source.length() != 2 || target.length() != 2) {
            throw new IllegalArgumentException("각 위치는 두글자 입니다. ex) 'a1', 'h8'");
        }
    }

    public MoveOption(List<String> options) {
        this(options.get(SOURCE_INDEX), options.get(TARGET_INDEX));
    }
}
