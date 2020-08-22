package ip.task;

public class Task {

    protected String description;
    protected boolean isDone;

    // instance variable for object ID
    protected int id;

    // class variable for number of Tasks
    private static int numTasks = 0;

    public Task(String description) {
        this.description = description;
        numTasks++;
        this.id = numTasks;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public static int getNumTasks() {
        return numTasks;
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
