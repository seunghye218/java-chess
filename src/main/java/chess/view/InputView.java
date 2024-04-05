package chess.view;

import chess.view.command.CommandType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int COMMAND_INDEX = 0;

    public Entry<CommandType, List<String>> readStart() {
        String[] commandAndOptions = SCANNER.nextLine().split(" ");
        CommandType commandType = CommandType.of(commandAndOptions[COMMAND_INDEX]);

        if (CommandType.START != commandType) {
            throw new IllegalArgumentException(String.format("게임 시작 시 %s 만 입력할 수 있습니다.", CommandType.START.getCommand()));
        }
        return Map.entry(commandType, getOptions(commandAndOptions));
    }

    public Entry<CommandType, List<String>> readCommand() {
        String[] commandAndOptions = SCANNER.nextLine().split(" ");
        CommandType commandType = CommandType.of(commandAndOptions[COMMAND_INDEX]);

        if (CommandType.START == commandType) {
            throw new IllegalArgumentException(String.format("게임 중에는 %s 를 입력할 수 없습니다.", CommandType.START.getCommand()));
        }
        return Map.entry(commandType, getOptions(commandAndOptions));
    }

    private List<String> getOptions(String[] commandAndOptions) {
        return Arrays.stream(commandAndOptions)
                .skip(1)
                .toList();
    }
}
