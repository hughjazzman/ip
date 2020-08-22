import ip.task.Task;
import ip.task.TaskManager;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Print logo and greeting
        printLogo();
        printGreeting();

        // task.Task list
        TaskManager taskManager = new TaskManager();

        // Scanner class for user input
        String line;
        Scanner in = new Scanner(System.in);

        // Parsing of user input
        do {
            line = in.nextLine();
            execute(line, taskManager);
        } while (!line.equals("bye"));

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
                " ▄▄▄▄▄ ██   ▄▄▄▄▄▄   ▄▄▄▄▄▄   \n" +
                "▄▀  █   █ █ ▀   ▄▄▀  ▀   ▄▄▀   \n" +
                "    █   █▄▄█ ▄▀▀   ▄▀ ▄▀▀   ▄▀ \n" +
                " ▄ █    █  █ ▀▀▀▀▀▀   ▀▀▀▀▀▀   \n" +
                "  ▀        █                   \n" +
                "          █                    \n" +
                "         ▀                ");
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

    private static void execute(String line, TaskManager taskManager) {
        // Prevent blank tasks
        if (line.isBlank()) {
            return;
        }
        // split using space as delimiter
        String[] words = line.split(" ");

        switch (words[0]) {
        case "list":
            printList(taskManager);
            break;
        case "done":
            execDone(taskManager, words[1]);
            break;
        case "bye":
            break;
        default:
            execAdd(taskManager, line);
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
        System.out.println(" " + task.getStatusIcon() + " " + task.getDescription());
        printHorizontalLine();
    }

    private static void execAdd(TaskManager taskManager, String line) {
        taskManager.addTask(line);
        printHorizontalLine();
        System.out.println(" added: " + line);
        printHorizontalLine();
    }
}
