package chess.view.command;

import java.util.List;
import java.util.Map.Entry;

public record Command(CommandType commandType, List<String> options) {

    public Command(Entry<CommandType, List<String>> entry) {
        this(entry.getKey(), entry.getValue());
    }

    public CommandType getCommandType() {
        return commandType;
    }
}
