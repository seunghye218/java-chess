package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String COMMAND_START = "start";
    private static final String COMMAND_MOVE = "move";
    private static final String COMMAND_END = "end";
    private static final String COMMAND_STATUS = "status";

    public void readStart() {
        if (!COMMAND_START.equals(SCANNER.nextLine())) {
            throw new IllegalArgumentException(String.format("게임 시작 시 %s 만 입력할 수 있습니다.",COMMAND_START ));
        }
    }

    public String readCommand() {
        String input = SCANNER.nextLine();
        if (!COMMAND_END.equals(input) && !COMMAND_STATUS.equals(input) && !input.startsWith(COMMAND_MOVE)) {
            throw new IllegalArgumentException(
                    String.format("게임 중에는 %s, %s, %s 명령만 입력할 수 있습니다.", COMMAND_MOVE, COMMAND_END, COMMAND_STATUS));
        }
        return input;
    }
}
