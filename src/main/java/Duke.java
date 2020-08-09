import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String horLine = "____________________________________________________________\n";

        String greeting = horLine +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                horLine;

        String bye = horLine + " Bye. Hope to see you again soon!\n" + horLine;

        String line;
        Scanner in = new Scanner(System.in);

        System.out.println(greeting);
        line = in.nextLine();

        while (!line.equals("bye")) {
            System.out.println(horLine + ' ' + line + '\n' + horLine);
            line = in.nextLine();
        }

        System.out.println(bye);

    }
}
