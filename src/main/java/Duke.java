import ip.task.Task;
import ip.task.TaskManager;

import java.util.Scanner;

public class Duke {
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
            execute(line, taskManager);
        } while (!line.equals("bye"));

        // Exit program
        printFarewell();
    }

    private static void printLine(int n) {
        System.out.println("-".repeat(n));
    }

    private static void printHorizontalLine() {
        printLine(60);
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
     * @param line Line of user input.
     * @param taskManager TaskManager object.
     */
    private static void execute(String line, TaskManager taskManager) {
        // Prevent blank tasks
        if (line.isBlank()) {
            return;
        }

        // Find position of first space
        int pos_space = line.indexOf(" ");
        // command is first word
        String command = pos_space > 0 ? line.substring(0, pos_space) : line;
        String num;

        switch (command) {
        case "list":
            printList(taskManager);
            break;
        case "done":
            num = line.substring(pos_space + 1);
            execDone(taskManager, num);
            break;
        case "bye":
            break;
        case "todo":
        case "deadline":
        case "event":
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
        // Get index of actual task
        int pos_desc = line.indexOf(" ") + 1, pos_by, pos_at;
        if (pos_desc <= 0) {
            printInvalid();
            return;
        }

        String description = line.substring(pos_desc);
        String by, at;
        Task task = null;

        switch (command) {
        case "todo":
            task = taskManager.addTodo(description);
            break;
        case "deadline":
            pos_by = description.indexOf("/by");
            by = description.substring(pos_by + 4);
            description = description.substring(0, pos_by - 1);
            task = taskManager.addDeadline(description, by);
            break;
        case "event":
            pos_at = description.indexOf("/at");
            at = description.substring(pos_at + 4);
            description = description.substring(0, pos_at - 1);
            task = taskManager.addEvent(description, at);
            break;

        }
        printHorizontalLine();
        if (task != null) {
            System.out.println(" Got it. I've added this task:\n  " +
                    task.toString() +
                    "\n Now you have " + TaskManager.getTasksCount() +
                    " tasks in the list.");
        }
        printHorizontalLine();
    }
}
