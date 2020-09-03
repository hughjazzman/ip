package ip.task;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {
    private static final int MAX_TASKS = 100;
    private final Task[] tasks = new Task[MAX_TASKS];
    private int tasksCount = 0;

    public TaskManager(String filename) {
        getValues(filename);
    }

    private void getValues(String filePath) {
        System.out.println(filePath);
        FileInputStream stream;
        try {
            stream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        ArrayList<String> lines = new ArrayList<>();
        try {
            parseLines(reader);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseLines(BufferedReader reader) throws IOException {

        String strLine;
        while ((strLine = reader.readLine()) != null) {
            System.out.println(strLine);
            String[] params = strLine.trim().split("\\s*[|]\\s*");

            Task task;
            switch (params[0]) {
            case "T":
                task = this.addTodo(params[2]);
                break;
            case "D":
                task = this.addDeadline(params[2], params[3]);
                break;
            case "E":
                task = this.addEvent(params[2], params[3]);
                break;
            default:
                return;
            }
            System.out.println(params[1].strip());
            if (params[1].strip().equals("1")) {
                System.out.println("here");
                task.markAsDone();
            }
            System.out.println(task.isDone);
        }
    }

    /**
     * Returns the current task count in the tasks array.
     *
     * @return Number of tasks.
     */
    public int getTasksCount() {
        return tasksCount;
    }

    /**
     * Adds a general Task object to the tasks array.
     *
     * @param task Task object.
     * @return the Task object passed in after being added to the array.
     */
    private Task addTask(Task task) {
        tasks[tasksCount] = task;
        tasksCount++;
        return task;
    }

    /**
     * Adds a Todo task to the tasks array.
     *
     * @param description Description of the task.
     * @return Task object.
     */
    public Task addTodo(String description) {
        Todo todo = new Todo(description);
        return addTask(todo);
    }

    /**
     * Adds a Deadline task to the tasks array.
     *
     * @param description Description of the task.
     * @param by Date/Time of the task deadline.
     * @return Task object.
     */
    public Task addDeadline(String description, String by) {
        Deadline deadline = new Deadline(description, by);
        return addTask(deadline);
    }

    /**
     * Adds an Event task to the tasks array.
     *
     * @param description Description of the task.
     * @param at Location of the task.
     * @return Task object.
     */
    public Task addEvent(String description, String at) {
        Event event = new Event(description, at);
        return addTask(event);
    }


    /**
     * Marks a Task as done.
     *
     * @param id ID of task corresponding to index in the tasks array.
     * @return Task object marked as done.
     */
    public Task markAsDone(int id) {
        Task task = tasks[id-1];
        task.markAsDone();
        return task;
    }

    /**
     * Prints a list of all tasks currently in the tasks array.
     */
    public void listTasks() {
        for (int i=0;i<tasksCount;i++) {
            System.out.println(" " + (i+1) + "." + tasks[i].toString() );
        }
    }


}
