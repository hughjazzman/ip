package ip.ui;

import ip.task.Task;
import ip.task.TaskManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    /** Number of dashes used in printed horizontal line **/
    private static final int NUM_DASHES = 60;
    private static final Scanner in = new Scanner(System.in);

    public String readCommand() {
        return in.nextLine().strip();
    }
    public void printHorizontalLine() {
        System.out.println("-".repeat(NUM_DASHES));
    }

    private void printLogo() {
        System.out.println(
                "    ___  ________  ________  ________\n" +
                        "   |\\  \\|\\   __  \\|\\_____  \\|\\_____  \\\n" +
                        "   \\ \\  \\ \\  \\|\\  \\\\|___/  /|\\|___/  /|\n" +
                        " __ \\ \\  \\ \\   __  \\   /  / /    /  / /\n" +
                        "|\\  \\\\_\\  \\ \\  \\ \\  \\ /  /_/__  /  /_/__\n" +
                        "\\ \\________\\ \\__\\ \\__\\\\________\\\\________\\\n" +
                        " \\|________|\\|__|\\|__|\\|_______|\\|_______|");
    }

    private void printGreeting() {
        printHorizontalLine();
        System.out.println(" Hey there! The name's Jazz.\n" +
                " What can I do for you?");
        printHorizontalLine();
    }

    public void showWelcome() {
        printLogo();
        printGreeting();
    }

    public void printFarewell() {
        System.out.println(" Bye. See you next time!");
    }

    public void printFileNotFound() {
        System.out.println(" File data.txt not found in src/main/resources... Creating new file data.txt.");
    }

    public void printFileError() {
        System.out.println(" Error reading/writing data.txt. Exiting.");
    }

    public void printInvalid() {
        printHorizontalLine();
        System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        printHorizontalLine();
    }

    public void printEmpty(String command) {
        printHorizontalLine();
        System.out.println(" ☹ OOPS!!! The description of a " + command + " cannot be empty.");
        printHorizontalLine();
    }

    public void printWrongFormatInteger() {
        printHorizontalLine();
        System.out.println(" The index of the task must be an integer.");
        printHorizontalLine();
    }

    public void printWrongFormatTask(String command, String parameter) {
        printHorizontalLine();
        System.out.println(" " + command + " requires the " + parameter + " parameter");
        printHorizontalLine();
    }

    public void printInvalidTask() {
        printHorizontalLine();
        System.out.println(" The task ID does not exist!");
        printHorizontalLine();
    }

    public void printFullTasks() {
        printHorizontalLine();
        System.out.println(" Sorry, unable to add any more tasks! The task manager is full. ");
        printHorizontalLine();
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void printAddedTask(Task task, TaskManager taskManager) {
        System.out.println(" Got it. I've added this task:\n  " +
                task.toString() +
                "\n Now you have " + taskManager.getTasksCount() +
                " tasks in the list.");
    }

    public void printDeleteTask(Task task, TaskManager taskManager) {
        System.out.println(" Noted. I've removed this task:\n   " +
                task.toString() + "\n Now you have " +
                taskManager.getTasksCount() + " tasks in the list.");
    }

    public void printDoneTask(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(" " + task.toString());
    }

    public void printListAllTasks(TaskManager taskManager) {
        System.out.println(" Here are the tasks in your list:");
        taskManager.listAllTasks();
    }

    public void printFilterTasks(ArrayList<Task> tasks, String desc) {
        if (tasks.isEmpty()) {
            System.out.println(" No matching tasks in your list (search: " + desc + ")");
            return;
        }
        System.out.println(" Here are the matching tasks in your list (search: " + desc + "):" );
        int i = 0;
        for (Task t : tasks) {
            System.out.println(" " + (i+1) + "." + tasks.get(i).toString() );
            i++;
        }

    }
}
