package ip.task;

public class TaskManager {
    private Task[] tasks = new Task[100];
    private static int tasksCount = 0;

    /**
     * Returns the current task count in the tasks array.
     * @return Number of tasks.
     */
    public static int getTasksCount() {
        return tasksCount;
    }

    /**
     * Adds a general Task object to the tasks array.
     *
     * @param task Task object
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
        return this.addTask(todo);
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
        return this.addTask(deadline);
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
        return this.addTask(event);
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
