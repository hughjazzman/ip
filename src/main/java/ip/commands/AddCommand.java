package ip.commands;

import ip.DukeException;
import ip.task.Task;
import ip.task.TaskManager;
import ip.ui.Ui;

public class AddCommand extends Command {
    /** Prefix strings that determine the command **/
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    /** Parameters for commands **/
    public static final String PARAM_BY = "/by";
    public static final String PARAM_AT = "/at";

    private final String command;
    private final String description;
    private final String param;

    /**
     * Constructor
     *
     * @param command Task of the AddCommand.
     * @param description Description of task.
     * @param param Parameter of task (if any).
     */
    public AddCommand(String command, String description, String param) {
        this.command = command;
        this.description = description;
        this.param = param;
    }

    /**
     * Overloaded Constructor.
     *
     * @param command Task of the AddCommand.
     * @param description Description of task.
     */
    public AddCommand(String command, String description) {
        this(command, description, "");
    }


    /**
     * Adds appropriate type of Task based on the given command.
     *
     * @param taskManager TaskManager object.
     * @param ui Ui object.
     * @throws DukeException If an IO Error occurs.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DukeException {
        Task task;
        switch (command) {
        case COMMAND_TODO:
            task = taskManager.addTodo(description);
            break;
        case COMMAND_DEADLINE:
            task = taskManager.addDeadline(description, param);
            break;
        case COMMAND_EVENT:
            task = taskManager.addEvent(description, param);
            break;
        default:
            throw new DukeException("Invalid Command!");
        }

        ui.printAddedTask(task, taskManager);
        super.execute(taskManager, ui);
    }


}
