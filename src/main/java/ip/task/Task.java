package ip.task;

public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns description of the Task.
     *
     * @return String description of Task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks a Task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns a boolean indicating if a Task is done.
     *
     * @return boolean if Task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a status icon depending on whether task isDone.
     *
     * @return Tick symbol if isDone else X symbol.
     */
    public String getStatusIcon() {
        return isDone ? "[/]" : "[X]"; //return tick or X symbols
    }

    /**
     * Returns a String representation of the Task, using the statusIcon and description.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}
