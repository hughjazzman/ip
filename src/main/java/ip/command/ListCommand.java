package ip.command;

import ip.task.TaskManager;
import ip.ui.Ui;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        System.out.println("Here are the tasks in your list:");
        taskManager.listTasks();
    }
}
