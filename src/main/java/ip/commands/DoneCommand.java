package ip.commands;

import ip.DukeException;
import ip.task.Task;
import ip.task.TaskManager;
import ip.ui.Ui;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";

    private final int num;

    /**
     * Constructor.
     *
     * @param num ID of task to mark as done.
     */
    public DoneCommand(int num) {
        this.num = num;
    }

    /**
     * Executes marking a task as done
     *
     * @param taskManager TaskManager object.
     * @param ui Ui object.
     * @throws DukeException If an IO Error occurs.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DukeException {
        Task task;
        // Check that id is valid
        try {
            task = taskManager.markAsDone(num);
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidTask();
            return;
        }

        ui.printDoneTask(task);
        super.execute(taskManager, ui);
    }
}
