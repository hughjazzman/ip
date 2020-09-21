package ip.ui;

import ip.task.Task;
import ip.task.TaskManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    /** Number of dashes used in printed horizontal line **/
    private static final int NUM_DASHES = 60;
    private static final Scanner in = new Scanner(System.in);

    /**
     * Returns the next line of user input.
     *
     * @return String of user input.
     */
    public String readCommand() {
        return in.nextLine().strip();
    }

    /**
     * Prints horizontal line as a marker between inputs.
     */
    public void printHorizontalLine() {
        System.out.println("-".repeat(NUM_DASHES));
    }

    /**
     * Prints Duke logo - JAZZ.
     */
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

    /**
     * Prints greeting to Duke.
     */
    private void printGreeting() {
        printHorizontalLine();
        System.out.println(" Hey there! The name's Jazz.\n" +
                " What can I do for you?");
        printHorizontalLine();
    }

    /**
     * Prints logo and greeting for Duke.
     */
    public void showWelcome() {
        printLogo();
        printGreeting();
    }

    /**
     * Prints farewell message after exiting Duke.
     */
    public void printFarewell() {
        System.out.println(" Bye. See you next time!");
    }

    /**
     * Prints that the file is not found.
     */
    public void printFileNotFound() {
        System.out.println(" File data.txt not found in src/main/resources... Creating new file data.txt.");
    }

    /**
     * Prints that invalid input has been received.
     */
    public void printInvalid() {
        printHorizontalLine();
        System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        printHorizontalLine();
    }

    /**
     * Prints that a command has an empty parameter.
     *
     * @param command Command that has the empty parameter.
     */
    public void printEmpty(String command) {
        printHorizontalLine();
        System.out.println(" ☹ OOPS!!! The description of a " + command + " cannot be empty.");
        printHorizontalLine();
    }

    /**
     * Prints that the index of the task must be an integer.
     */
    public void printWrongFormatInteger() {
        System.out.println(" The index of the task must be an integer.");
    }

    /**
     * Prints that a task has the wrong format.
     *
     * @param command Command that has wrong format.
     * @param parameter Parameter which is missing.
     */
    public void printWrongFormatTask(String command, String parameter) {
        System.out.println(" " + command + " requires the " + parameter + " parameter");
    }

    /**
     * Prints that a Task ID does not exist.
     */
    public void printInvalidTask() {
        System.out.println(" The task ID does not exist!");
    }

    /**
     * Prints a message.
     *
     * @param message Message.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints that a Task has been added.
     *
     * @param task Task object that was added.
     * @param taskManager TaskManager object to obtain taskCount.
     */
    public void printAddedTask(Task task, TaskManager taskManager) {
        System.out.println(" Got it. I've added this task:\n  " +
                task.toString() +
                "\n Now you have " + taskManager.getTasksCount() +
                " tasks in the list.");
    }

    /**
     * Prints that a Task has been deleted.
     *
     * @param task Task object that was deleted.
     * @param taskManager TaskManager object to obtain taskCount.
     */
    public void printDeleteTask(Task task, TaskManager taskManager) {
        System.out.println(" Noted. I've removed this task:\n   " +
                task.toString() + "\n Now you have " +
                taskManager.getTasksCount() + " tasks in the list.");
    }

    /**
     * Prints that a Task is marked as done.
     *
     * @param task Task object marked as done.
     */
    public void printDoneTask(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(" " + task.toString());
    }


    /**
     * Prints all the Tasks in the TaskManager.
     *
     * @param taskManager TaskManager object containing the Tasks.
     */
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
