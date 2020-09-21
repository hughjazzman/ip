package ip.task;

public class Deadline extends Task {


    /**
     * Constructor.
     *
     * @param description Description of Task.
     * @param by Deadline of Task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.param = by;
    }

    public String getBy() {
        return super.getParam();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + param + ")";
    }
}