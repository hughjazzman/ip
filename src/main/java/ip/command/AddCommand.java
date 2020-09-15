package ip.command;

import ip.DukeException;
import ip.parser.Parser;
import ip.task.Task;
import ip.task.TaskManager;
import ip.ui.Ui;

public class AddCommand extends Command {
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    /** Parameters for commands **/
    private static final String PARAM_BY = "/by";
    private static final String PARAM_AT = "/at";

    private Parser parser;
    private String command;
    private String line;

    public AddCommand(String command, String line) {
        this.command = command;
        this.line = line;
    }


    /**
     * Adds appropriate type of Task based on the given command.
     *
     * @param taskManager TaskManager object.
     * @param ui Ui object.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
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
                    String[] details = Parser.parseTask(descriptionParam, COMMAND_DEADLINE, PARAM_BY, byPos);
                    by = details[0];
                    description = details[1];
                } catch (StringIndexOutOfBoundsException | DukeException e) {
                    return;
                }

                task = taskManager.addDeadline(description, by);
                break;
            case COMMAND_EVENT:
                atPos = descriptionParam.indexOf(PARAM_AT);
                try {
                    String[] details = Parser.parseTask(descriptionParam, COMMAND_EVENT, PARAM_AT, atPos);
                    at = details[0];
                    description = details[1];
                } catch (StringIndexOutOfBoundsException | DukeException e) {
                    return;
                }

                task = taskManager.addEvent(description, at);
                break;
            default:
                ui.printInvalid();
                return;
        }

        System.out.println(" Got it. I've added this task:\n  " +
                task.toString() +
                "\n Now you have " + taskManager.getTasksCount() +
                " tasks in the list.");
    }


}
