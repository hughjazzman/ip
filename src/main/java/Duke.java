import ip.task.Task;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Horizontal Line reused
        String horLine = "____________________________________________________________";

        // Greeting and Farewell at start and end
        String greeting = horLine +
                "\n Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                horLine;

        String farewell = horLine + "\n Bye. Hope to see you again soon!\n" + horLine;

        // task.Task list
        Task[] taskList = new Task[100];

        // Scanner class for user input
        String line;
        Scanner in = new Scanner(System.in);

        // Print greeting
        System.out.println(greeting);

        // Parsing of user input
        line = in.nextLine();

        while (!line.equals("bye")) {
            // split using space as delimiter
            String[] words = line.split(" ");

            // Print taskList
            if (line.equals("list")) {
                System.out.println(horLine + "\n Here are the tasks in your list:");
                for (int i = 0; i < Task.getNumTasks(); i++) {
                    System.out.println(" " + (i+1) + "." +
                            taskList[i].getStatusIcon() +
                            " " + taskList[i].getDescription() );
                }
                System.out.println(horLine);
            } else if (words[0].equals("done")) { // Mark as done
                int id = Integer.parseInt(words[1]);
                Task task = taskList[id-1];
                task.markAsDone();
                System.out.println(horLine + "\n Nice! I've marked this task as done:\n   " +
                         task.getStatusIcon() + " " + task.getDescription() + '\n' + horLine);
            } else { // Add to taskList
                Task newTask = new Task(line);
                taskList[newTask.getId()-1] = newTask;
                System.out.println(horLine + "\n added: " + line + '\n' + horLine);
            }
            line = in.nextLine();
        }

        // Print farewell
        System.out.println(farewell);

    }
}
