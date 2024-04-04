package chess.view.command;

import java.util.List;

public class MoveOption {

    private static final int SOURCE_INDEX = 0;
    private static final int TARGET_INDEX = 1;

    private final String source;
    private final String target;


    public MoveOption(List<String> options) {
        if (options.size() != 2) {
            throw new IllegalArgumentException("move 명령은 두 개의 위치를 입력해야 합니다.");
        }
        if (options.get(SOURCE_INDEX).length() != 2 || options.get(TARGET_INDEX).length() != 2) {
            throw new IllegalArgumentException("각 위치는 두글자 입니다. ex) 'a1', 'h8'");
        }
        this.source = options.get(SOURCE_INDEX);
        this.target = options.get(TARGET_INDEX);
    }

    public String source() {
        return source;
    }

    public String target() {
        return target;
    }
}
