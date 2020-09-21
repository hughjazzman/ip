package ip.task;

import ip.DukeException;
import ip.file.FileManager;
import ip.ui.Ui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private int tasksCount = 0;
    private final FileManager fileManager;

    /**
     * Constructor.
     *
     * @param fileManager FileManager of file being parsed by TaskManager.
     * @throws DukeException If an I/O error occurs.
     * @throws FileNotFoundException If the file does not exist at filePath.
     */
    public TaskManager(FileManager fileManager) throws FileNotFoundException, DukeException {
        this.fileManager = fileManager;
        fileManager.getLines(this);
    }

    /**
     * Returns TaskManager object given an input FileManager.
     *
     * @param fileManager FileManager of a file.
     * @return TaskManager object to keep track of tasks.
     * @throws DukeException If an I/O error occurs.
     */
    public static TaskManager createTaskManager(FileManager fileManager, Ui ui) throws DukeException {

        // Will loop as long as FileNotFoundException is caught
        while (true) {
            try {
                return new TaskManager(fileManager);
            } catch (FileNotFoundException e) {
                ui.printFileNotFound();
                // Create file if not found
                fileManager.createFile();
            }
        }
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
     * @throws DukeException If an I/O error occurs, or if invalid Task type is parsed.
     */
    public void writeToFile() throws DukeException {
        StringBuilder lines = new StringBuilder();
        String type, isDone, desc, param;
        String delimiter = " | ";
        for (int i = 0; i < tasksCount; i++) {
            Task task = getTask(i);
            desc = task.getDescription();
            if (task instanceof Todo) {
                type = "T";
                param = "";
            } else if (task instanceof Deadline) {
                type = "D";
                param = ((Deadline) task).getBy();
            } else if (task instanceof Event) {
                type = "E";
                param = ((Event) task).getAt();
            } else {
                throw new DukeException("Invalid task type");
            }
            isDone = task.isDone() ? "1" : "0";
            lines.append(type).append(delimiter).append(isDone).append(delimiter)
                    .append(desc).append(delimiter).append(param).append('\n');
        }
        // Writes to file
        fileManager.writeFile(lines.toString());
    }

    private Task getTask(int id) {
        return tasks.get(id);
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
        tasks.add(task);
        tasksCount++;
        return task;
    }

    /**
     * Deletes task based on their id in tasks.
     *
     * @param id The id in the tasks list.
     * @return Task object deleted.
     */
    public Task deleteTask(int id) {
        Task task = tasks.get(id-1);
        tasks.remove(id-1);
        tasksCount--;
        return task;
    }

    /**
     * Adds a Todo task to the tasks array.
     *
     * @param description Description of the task.
     * @return Todo object.
     */
    public Todo addTodo(String description) {
        Todo todo = new Todo(description);
        return (Todo) addTask(todo);
    }

    /**
     * Adds a Deadline task to the tasks array.
     *
     * @param description Description of the task.
     * @param by Date/Time of the task deadline.
     * @return Deadline object.
     */
    public Deadline addDeadline(String description, String by) {
        Deadline deadline = new Deadline(description, by);
        return (Deadline) addTask(deadline);
    }

    /**
     * Adds an Event task to the tasks array.
     *
     * @param description Description of the task.
     * @param at Location of the task.
     * @return Event object.
     */
    public Event addEvent(String description, String at) {
        Event event = new Event(description, at);
        return (Event) addTask(event);
    }


    /**
     * Marks a Task as done.
     *
     * @param id ID of task corresponding to index in the tasks array.
     * @return Task object marked as done.
     */
    public Task markAsDone(int id) {
        Task task = tasks.get(id - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Prints a list of all tasks currently in the tasks array.
     */
    public void listTasks() {
        for (int i = 0; i < tasksCount; i++) {
            System.out.println(" " + (i+1) + "." + tasks.get(i).toString() );
        }
    }


}
