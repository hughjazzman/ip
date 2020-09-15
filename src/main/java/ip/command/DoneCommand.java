package ip.command;

import ip.task.Task;
import ip.task.TaskManager;
import ip.ui.Ui;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";

    private String num;

    public DoneCommand(String num) {
        this.num = num;
    }

    /**
     * Executes marking a task as done
     *
     * @param taskManager TaskManager object.
     * @param ui Ui object.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        int id;

        // Check for a valid integer as input
        try {
            id = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            ui.printWrongFormatInteger();
            return;
        }

        Task task;
        // Check that id is valid
        try {
            task = taskManager.markAsDone(id);
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidTask();
            return;
        }
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(" " + task.toString());
    }
}
