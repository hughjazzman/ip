package ip.task;

public class Deadline extends Task {

    protected String by;

    /**
     * Constructor.
     *
     * @param description Description of Task.
     * @param by Deadline of Task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the by parameter of the Deadline.
     *
     * @return String of by parameter.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns String representation of Deadline.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}