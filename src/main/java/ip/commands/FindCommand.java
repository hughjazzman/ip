package ip.commands;

import ip.DukeException;
import ip.task.Task;
import ip.task.TaskManager;
import ip.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private final String description;

    /**
     * Constructor.
     *
     * @param description User input of keyword(s) to be searched.
     */
    public FindCommand(String description) {
        this.description = description.toLowerCase();
    }

    /**
     * Executes the find command, finding all tasks using a keyword.
     *
     * @param taskManager TaskManager object.
     * @param ui Ui object.
     * @throws DukeException If an I/O error occurs.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DukeException {
        ArrayList<Task> tasks = taskManager.findTasksByDesc(description);

        ui.printFilterTasks(tasks, description);

        super.execute(taskManager, ui);
    }
}
