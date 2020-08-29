import ip.task.Task;
import ip.task.TaskManager;

import java.util.Scanner;

public class Duke {
    /** Number of dashes used in printed horizontal line **/
    private static final int NUM_DASHES = 60;
    /** Prefix strings that determine the command **/
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    /** Parameters for commands **/
    private static final String PARAM_BY = "/by";
    private static final String PARAM_AT = "/at";

    public static void main(String[] args) {
        // Print logo and greeting
        printLogo();
        printGreeting();

        // Create TaskManager
        TaskManager taskManager = new TaskManager();

        // Scanner class for user input
        String line;
        Scanner in = new Scanner(System.in);

        // Parsing of user input
        do {
            line = in.nextLine().strip();
            execute(taskManager, line);
        } while (!line.equals("bye"));

        // Exit program
        printFarewell();
    }

    private static void printHorizontalLine() {
        System.out.println("-".repeat(NUM_DASHES));
    }

    private static void printLogo() {
        System.out.println(
                "    ___  ________  ________  ________\n" +
                "   |\\  \\|\\   __  \\|\\_____  \\|\\_____  \\\n" +
                "   \\ \\  \\ \\  \\|\\  \\\\|___/  /|\\|___/  /|\n" +
                " __ \\ \\  \\ \\   __  \\   /  / /    /  / /\n" +
                "|\\  \\\\_\\  \\ \\  \\ \\  \\ /  /_/__  /  /_/__\n" +
                "\\ \\________\\ \\__\\ \\__\\\\________\\\\________\\\n" +
                " \\|________|\\|__|\\|__|\\|_______|\\|_______|");
    }

    private static void printGreeting() {
        printHorizontalLine();
        System.out.println(" Hey there! The name's Jazz.\n" +
                " What can I do for you?");
        printHorizontalLine();
    }

    private static void printFarewell() {
        printHorizontalLine();
        System.out.println(" Bye. See you next time!");
        printHorizontalLine();
    }

    private static void printInvalid() {
        printHorizontalLine();
        System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        printHorizontalLine();
    }

    private static void printEmpty(String command) {
        printHorizontalLine();
        System.out.println(" ☹ OOPS!!! The description of a " + command + " cannot be empty.");
        printHorizontalLine();
    }

    private static void printList(TaskManager taskManager) {
        printHorizontalLine();
        System.out.println("Here are the tasks in your list:");
        taskManager.listTasks();
        printHorizontalLine();
    }

    private static void printWrongFormatInteger() {
        printHorizontalLine();
        System.out.println(" The index of the task must be an integer.");
        printHorizontalLine();
    }

    private static void printWrongFormatTask(String command, String parameter) {
        printHorizontalLine();
        System.out.println(" " + command + " requires the " + parameter + " parameter");
        printHorizontalLine();
    }

    private static void printInvalidTask() {
        printHorizontalLine();
        System.out.println(" The task ID does not exist!");
        printHorizontalLine();
    }

    /**
     * Executes appropriate methods based on the given command.
     *
     * @param taskManager TaskManager object.
     * @param line Line of user input.
     */
    private static void execute(TaskManager taskManager, String line) {
        // Prevent blank tasks
        if (line.isBlank()) {
            return;
        }

        // Find position of first space
        int spacePos = line.indexOf(" ");
        // command is first word
        String command = spacePos > 0 ? line.substring(0, spacePos).strip() : line;
        String num;

        switch (command) {
        case COMMAND_LIST:
            printList(taskManager);
            break;
        case COMMAND_DONE:
            num = line.substring(spacePos + 1).strip();
            execDone(taskManager, num);
            break;
        case COMMAND_BYE:
            break;
        case COMMAND_TODO: // Fallthrough
        case COMMAND_DEADLINE: // Fallthrough
        case COMMAND_EVENT:
            execAddTask(taskManager, command, line);
            break;
        default:
            printInvalid();
            break;
        }
    }

    /**
     * Executes marking a task as done
     *
     * @param taskManager TaskManager object.
     * @param num ID number of task in list.
     */
    private static void execDone(TaskManager taskManager, String num) {
        int id;

        try {
            id = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            printWrongFormatInteger();
            return;
        }

        Task task;
        try {
            task = taskManager.markAsDone(id);
        } catch (ArrayIndexOutOfBoundsException e) {
            printInvalidTask();
            return;
        }
        printHorizontalLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(" " + task.toString());
        printHorizontalLine();
    }

    /**
     * Adds appropriate type of Task based on the given command.
     *
     * @param taskManager TaskManager object.
     * @param command Command determined from first word of the line of user input.
     * @param line Rest of the line of user input.
     */
    private static void execAddTask(TaskManager taskManager, String command, String line) {
        // Position of task description, /by marker, and /at marker
        int descPos = line.indexOf(" "), byPos, atPos;
        String descriptionParam, description;

        // Check for blank description
        try {
            descriptionParam = line.substring(descPos).strip();
        } catch (StringIndexOutOfBoundsException e) {
            printEmpty(command);
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
                String[] details = parseTask(descriptionParam, COMMAND_DEADLINE, PARAM_BY, byPos);
                by = details[0];
                description = details[1];
            } catch (Exception e) {
                return;
            }

            task = taskManager.addDeadline(description, by);
            break;
        case COMMAND_EVENT:
            atPos = descriptionParam.indexOf(PARAM_AT);
            try {
                String[] details = parseTask(descriptionParam, COMMAND_EVENT, PARAM_AT, atPos);
                at = details[0];
                description = details[1];
            } catch (Exception e) {
                return;
            }

            task = taskManager.addEvent(description, at);
            break;
        default:
            printInvalid();
            return;
        }

        printHorizontalLine();
        System.out.println(" Got it. I've added this task:\n  " +
                task.toString() +
                "\n Now you have " + taskManager.getTasksCount() +
                " tasks in the list.");
        printHorizontalLine();
    }

    /**
     * Parses the parameters of the user input. Used in parseTask.
     *
     * @param descriptionParam User input after the command.
     * @param command Command name.
     * @param param Parameter name.
     * @param paramPos Parameter index in descriptionParam.
     * @return paramDetails User input for the param.
     * @throws StringIndexOutOfBoundsException If paramPos is < 0 (missing param)
     *         or paramPos is > descriptionParam.length() (blank param)
     */
    private static String parseParam(
            String descriptionParam, String command, String param, int paramPos)
            throws StringIndexOutOfBoundsException {
        String paramDetails;
        try {
            paramDetails = descriptionParam.substring(paramPos + param.length() + 1).strip();
            return paramDetails;
        } catch (StringIndexOutOfBoundsException e) {
            // Check that there is a parameter
            if (paramPos < 0) {
                printWrongFormatTask(command, param);
            } else { // Check for blank parameter
                printEmpty(command + " " + param + " parameter");
            }
            throw e;
        }
    }

    /**
     * Parses the description of the user input. Used in parseTask.
     *
     * @param descriptionParam User input after the command.
     * @param command Command name.
     * @param param Parameter name.
     * @param paramPos Parameter index in descriptionParam.
     * @return description User input for the command description.
     * @throws StringIndexOutOfBoundsException If paramPos <= 0 (missing description)
     */
    private static String parseDesc(
            String descriptionParam, String command, String param, int paramPos)
            throws StringIndexOutOfBoundsException {
        String description;
        try {
            description = descriptionParam.substring(0, paramPos - 1).strip();
            return description;
        } catch (StringIndexOutOfBoundsException e) {
            printEmpty(command);
            throw e;
        }
    }

    /**
     * Parses the entire task given as user input with description and parameters.
     * Used in execAddTask.
     *
     * @param descriptionParam User input after the command.
     * @param command Command name.
     * @param param Parameter name.
     * @param paramPos Parameter index in descriptionParam.
     * @return details String array of paramDetails and description of task.
     * @throws RuntimeException If parsed description is blank.
     */
    private static String[] parseTask(
            String descriptionParam, String command, String param, int paramPos)
            throws RuntimeException {
        String paramDetails, description;
        String[] details = new String[2];

        paramDetails = parseParam(descriptionParam, command, param, paramPos);
        description = parseDesc(descriptionParam, command, param, paramPos);

        if (description.isBlank()) {
            printEmpty(command);
            throw new RuntimeException();
        }

        details[0] = paramDetails;
        details[1] = description;

        return details;

    }
}
