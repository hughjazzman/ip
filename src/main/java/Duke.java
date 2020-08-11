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

        String bye = horLine + "\n Bye. Hope to see you again soon!\n" + horLine;

        // To do list and number of items tracker
        String[] toDoList = new String[100];
        int numItems = 0;

        // Scanner class for user input
        String line;
        Scanner in = new Scanner(System.in);

        // Print greeting
        System.out.println(greeting);

        // Parsing of user input
        line = in.nextLine();

        while (!line.equals("bye")) {
            // Print toDoList
            if (line.equals("list")) {
                System.out.println(horLine);
                for (int i = 1; i <= numItems; i++) {
                    System.out.println(" " + i + ". " + toDoList[i-1] );
                }
                System.out.println(horLine);
            } else { // Add to toDoList
                toDoList[numItems++] = line;
                System.out.println(horLine + "\n added: " + line + '\n' + horLine);
            }
            line = in.nextLine();
        }

        // Print farewell
        System.out.println(bye);

    }
}
