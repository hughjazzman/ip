package ip.task;

import java.util.ArrayList;

public class TaskManager {
    private static final int MAX_TASKS = 100;
    private final ArrayList<Task> tasks = new ArrayList<>();
    private int tasksCount = 0;

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
        Task task = tasks.get(id-1);
        task.markAsDone();
        return task;
    }

    /**
     * Prints a list of all tasks currently in the tasks array.
     */
    public void listTasks() {
        for (int i=0;i<tasksCount;i++) {
            System.out.println(" " + (i+1) + "." + tasks.get(i).toString() );
        }
    }


}
