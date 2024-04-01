package chess.view.command;

public enum CommandType {
    START("start"),
    END("end"),
    STATUS("status"),
    MOVE("move");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public static CommandType of(String command) {
        for (CommandType value : values()) {
            if (value.command.equals(command)) {
                return value;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 명령입니다.");
    }

    public String getCommand() {
        return command;
    }
}
