package ip.command;

import ip.task.Task;
import ip.task.TaskManager;
import ip.ui.Ui;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private String num;

    public DeleteCommand(String num) {
        this.num = num;
    }

    /**
     * Executes deleting a task
     *
     * @param taskManager TaskManager object.
     * @param ui Ui object.
     */
    public void execute(TaskManager taskManager, Ui ui) {
        int id;

        try {
            id = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            ui.printWrongFormatInteger();
            return;
        }

        Task task;
        try {
            task = taskManager.deleteTask(id);
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidTask();
            return;
        }
        ui.printDeleteTask(task, taskManager);

    }
}
