package ip.commands;

import ip.DukeException;
import ip.task.Task;
import ip.task.TaskManager;
import ip.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private final String line;

    public FindCommand(String line) {
        this.line = line.toLowerCase();
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DukeException {
        // Position of find description
        int descPos = line.indexOf(" ");
        String description;

        // Check for blank description
        try {
            description = line.substring(descPos).strip();
        } catch (StringIndexOutOfBoundsException e) {
            ui.printEmpty(COMMAND_WORD);
            return;
        }

        ArrayList<Task> tasks = taskManager.findTasksByDesc(description);
        ui.printFilterTasks(tasks, description);

        super.execute(taskManager, ui);
    }
}
