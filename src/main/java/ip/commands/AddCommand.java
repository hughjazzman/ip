package ip.commands;

import ip.DukeException;
import ip.parser.Parser;
import ip.task.Task;
import ip.task.TaskManager;
import ip.ui.Ui;

public class AddCommand extends Command {
    /** Prefix strings that determine the command **/
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    /** Parameters for commands **/
    private static final String PARAM_BY = "/by";
    private static final String PARAM_AT = "/at";

    private final Parser parser;
    private final String command;
    private final String line;

    public AddCommand(String command, String line) {
        this.command = command;
        this.line = line;
        parser = new Parser();
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
        // Position of task description, /by marker, and /at marker
        int descPos = line.indexOf(" "), byPos, atPos;
        String descriptionParam, description;

        // Check for blank description
        try {
            descriptionParam = line.substring(descPos).strip();
        } catch (StringIndexOutOfBoundsException e) {
            ui.printEmpty(command);
            return;
        }

        String by, at;
        Task task;

        switch (command) {
        case COMMAND_TODO:
            task = taskManager.addTodo(descriptionParam);
            break;
        case COMMAND_DEADLINE:
            byPos = descriptionParam.indexOf(PARAM_BY);

            try {
                String[] details = parser.parseTask(descriptionParam, COMMAND_DEADLINE, PARAM_BY, byPos);
                by = details[0];
                description = details[1];
            } catch (StringIndexOutOfBoundsException e) {
                return;
            }

            task = taskManager.addDeadline(description, by);
            break;
        case COMMAND_EVENT:
            atPos = descriptionParam.indexOf(PARAM_AT);
            try {
                String[] details = parser.parseTask(descriptionParam, COMMAND_EVENT, PARAM_AT, atPos);
                at = details[0];
                description = details[1];
            } catch (StringIndexOutOfBoundsException e) {
                return;
            }

            task = taskManager.addEvent(description, at);
            break;
        default:
            throw new DukeException("Invalid Command!");
        }

        ui.printAddedTask(task, taskManager);
        super.execute(taskManager, ui);
    }


}
