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

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return isDone ? "[\u2713]" : "[\u2718]"; //return tick or X symbols
    }

    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
