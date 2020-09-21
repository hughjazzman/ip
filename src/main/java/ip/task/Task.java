package ip.task;

public class Task {

    protected String description;

    protected String param;
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


    public String getDescription() {
        return description;
    }

    protected String getParam() {
        return param;
    }

    public void markAsDone() {
        isDone = true;
    }

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
