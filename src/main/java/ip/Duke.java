package ip;

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
            line = in.nextLine();
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
        System.out.println(" Invalid Command!");
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
        String command = spacePos > 0 ? line.substring(0, spacePos) : line;
        String num;

        switch (command) {
        case COMMAND_LIST:
            printList(taskManager);
            break;
        case COMMAND_DONE:
            num = line.substring(spacePos + 1);
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

    private static void printList(TaskManager taskManager) {
        printHorizontalLine();
        System.out.println("Here are the tasks in your list:");
        taskManager.listTasks();
        printHorizontalLine();
    }

    private static void execDone(TaskManager taskManager, String num) {
        int id = Integer.parseInt(num);
        Task task = taskManager.markAsDone(id);
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
        int descPos = line.indexOf(" ") + 1, byPos, atPos;
        // Check for blank description
        if (descPos <= 0) {
            printInvalid();
            return;
        }

        String description = line.substring(descPos);
        String by, at;
        Task task;

        switch (command) {
        case COMMAND_TODO:
            task = taskManager.addTodo(description);
            break;
        case COMMAND_DEADLINE:
            byPos = description.indexOf("/by");
            by = description.substring(byPos + 4);
            description = description.substring(0, byPos - 1);
            task = taskManager.addDeadline(description, by);
            break;
        case COMMAND_EVENT:
            atPos = description.indexOf("/at");
            at = description.substring(atPos + 4);
            description = description.substring(0, atPos - 1);
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
}
