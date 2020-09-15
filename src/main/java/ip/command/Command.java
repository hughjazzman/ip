package ip.command;

import ip.DukeException;
import ip.task.Task;
import ip.task.TaskManager;
import ip.ui.Ui;

import java.io.IOException;

public class Command {
    /** Prefix strings that determine the command **/
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";

    protected boolean isExit;

    /**
     * Executes appropriate methods based on the given command.
     *
     * @param taskManager TaskManager object.
     * @param ui Ui object.
     */
    public void execute(TaskManager taskManager, Ui ui) {
//        // Prevent blank tasks
//        if (line.isBlank()) {
//            return;
//        }
//
//        // Find position of first space
//        int spacePos = line.indexOf(" ");
//        // command is first word
//        String command = spacePos > 0 ? line.substring(0, spacePos).strip() : line;
//        String num;

//        switch (command) {
//            case COMMAND_LIST:
//                printList(taskManager);
//                return;
//            case COMMAND_DONE:
//                num = line.substring(spacePos + 1).strip();
//                execDone(taskManager, num);
//                break;
//            case COMMAND_DELETE:
//                num = line.substring(spacePos + 1).strip();
//                execDeleteTask(taskManager, num);
//                break;
//            case COMMAND_BYE:
//                return;
//            case COMMAND_TODO: // Fallthrough
//            case COMMAND_DEADLINE: // Fallthrough
//            case COMMAND_EVENT:
//                try {
//                    execAddTask(taskManager, command, line);
//                } catch (IndexOutOfBoundsException e) {
//                    printFullTasks();
//                }
//                break;
//            default:
//                printInvalid();
//                return;
//        }
        // Writes to file
        try {
            taskManager.writeToFile();
        } catch (IOException e) {
            ui.printFileError();
        }

    }

    public boolean isExit() {
        return isExit;
    }








}
