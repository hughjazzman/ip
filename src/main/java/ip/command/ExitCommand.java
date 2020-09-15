package ip.command;

import ip.task.TaskManager;
import ip.ui.Ui;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public ExitCommand() {
        isExit = true;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.printFarewell();
    }
}
