package ip.task;

import ip.file.FileManager;

import java.io.BufferedReader;
import java.io.IOException;

public class TaskManager {
    private static final int MAX_TASKS = 100;
    private final Task[] tasks = new Task[MAX_TASKS];
    private int tasksCount = 0;
    private FileManager fileManager;



    /**
     * Constructor.
     *
     * @param fileManager FileManager of file being parsed by TaskManager.
     * @throws IOException If an I/O error occurs.
     */
    public TaskManager(FileManager fileManager) throws IOException {
        this.fileManager = fileManager;
        fileManager.getLines(this);
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    /**
     * Parses the lines within the file.
     *
     * @param reader Reads text from the input stream from the file given in getLines().
     * @throws IOException If an I/O error occurs.
     */
    public void parseLines(BufferedReader reader) throws IOException {
        // strLine format:
        // <command> | <isDone> | <description> | <param/optional>
        // e.g. D | 1 | homework | yesterday
        String strLine;
        while ((strLine = reader.readLine()) != null) {
            // Solution below adapted from https://stackoverflow.com/a/41953571
            // Splits line using "|" and the whitespace surrounding it as the delimiter
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
            if (params[1].strip().equals("1")) {
                task.markAsDone();
            }
        }
    }

    /**
     * Write data to file.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void writeToFile() throws IOException {
        StringBuilder lines = new StringBuilder();
        String type, isDone, desc, param;
        String delimiter = " | ";
        boolean hasParam;
        for (int i = 0; i < tasksCount; i++) {
            Task task = tasks[i];
            if (task instanceof Todo) {
                type = "T";
                desc = task.getDescription();
                param = "";
                hasParam = false;
            } else if (task instanceof Deadline) {
                type = "D";
                desc = task.getDescription();
                param = ((Deadline) task).getBy();
                hasParam = true;
            } else if (task instanceof Event) {
                type = "E";
                desc = task.getDescription();
                param = ((Event) task).getAt();
                hasParam = true;
            } else {
                return;
            }
            isDone = task.isDone() ? "1" : "0";
            lines.append(type).append(delimiter).append(isDone).append(delimiter).append(desc);
            if (hasParam) {
                lines.append(delimiter).append(param);
            }
            lines.append('\n');
        }
        // Writes to file
        fileManager.writeFile(lines.toString());
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
