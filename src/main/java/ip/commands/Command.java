package ip.commands;

import ip.DukeException;
import ip.task.TaskManager;
import ip.ui.Ui;

import java.io.IOException;

public class Command {
    /** boolean whether the command is to exit **/
    protected boolean isExit;

    /**
     * Executes appropriate methods based on the given command.
     *
     * @param taskManager TaskManager object.
     * @param ui Ui object.
     */
    public void execute(TaskManager taskManager, Ui ui) throws DukeException {
        // Writes to file
        taskManager.writeToFile();
    }

    /**
     * Returns boolean isExit.
     *
     * @return boolean isExit whether the command is to exit.
     */
    public boolean isExit() {
        return isExit;
    }
}
