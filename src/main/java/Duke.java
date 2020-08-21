import ip.task.Task;
import ip.task.TaskManager;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo =
                " ▄▄▄▄▄ ██   ▄▄▄▄▄▄   ▄▄▄▄▄▄   \n" +
                "▄▀  █   █ █ ▀   ▄▄▀  ▀   ▄▄▀   \n" +
                "    █   █▄▄█ ▄▀▀   ▄▀ ▄▀▀   ▄▀ \n" +
                " ▄ █    █  █ ▀▀▀▀▀▀   ▀▀▀▀▀▀   \n" +
                "  ▀        █                   \n" +
                "          █                    \n" +
                "         ▀                \n";

        // Horizontal Line reused
        String horLine = "____________________________________________________________";

        // Greeting and Farewell at start and end
        String greeting = horLine +
                "\n Hey there! The name's Jazz.\n" +
                " What can I do for you?\n" +
                horLine;

        String farewell = horLine + "\n Bye. See you next time!\n" + horLine;

        // task.Task list
        TaskManager taskManager = new TaskManager();

        // Scanner class for user input
        String line;
        Scanner in = new Scanner(System.in);

        // Print greeting
        System.out.println(logo + greeting);

        // Parsing of user input
        line = in.nextLine();

        while (!line.equals("bye")) {
            // Prevent blank tasks
            if (line.isBlank()) {
                line = in.nextLine();
                continue;
            }
            // split using space as delimiter
            String[] words = line.split(" ");

            // Print taskList
            if (line.equals("list")) {
                System.out.println(horLine + "\n Here are the tasks in your list:");
                taskManager.listTasks();
                System.out.println(horLine);
            } else if (words[0].equals("done")) { // Mark as done
                int id = Integer.parseInt(words[1]);
                Task task = taskManager.markAsDone(id);
                System.out.println(horLine + "\n Nice! I've marked this task as done:\n   " +
                         task.getStatusIcon() + " " + task.getDescription() + '\n' + horLine);
            } else { // Add to taskList
                taskManager.addTask(line);
                System.out.println(horLine + "\n added: " + line + '\n' + horLine);
            }
            line = in.nextLine();
        }

        // Print farewell
        System.out.println(farewell);

    }
}
