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
        int spacePos = line.indexOf(" ");
        // command is first word
        String command = spacePos > 0 ? line.substring(0, spacePos) : line;
        String num;

        switch (command) {
        case "list":
            printList(taskManager);
            break;
        case "done":
            num = line.substring(spacePos + 1);
            execDone(taskManager, num);
            break;
        case "bye":
            break;
        case "todo": // Fallthrough
        case "deadline": // Fallthrough
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
        case "todo":
            task = taskManager.addTodo(description);
            break;
        case "deadline":
            byPos = description.indexOf("/by");
            by = description.substring(byPos + 4);
            description = description.substring(0, byPos - 1);
            task = taskManager.addDeadline(description, by);
            break;
        case "event":
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
                "\n Now you have " + TaskManager.getTasksCount() +
                " tasks in the list.");
        printHorizontalLine();
    }
}
