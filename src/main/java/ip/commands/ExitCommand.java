package ip.commands;

import ip.task.TaskManager;
import ip.ui.Ui;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Constructor.
     */
    public ExitCommand() {
        isExit = true;
    }

    /**
     * Executes the exit command message.
     *
     * @param taskManager TaskManager object.
     * @param ui Ui object.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.printFarewell();
    }
}
