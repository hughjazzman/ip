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
        setDateTime(by);
    }

    /**
     * Returns the by parameter of the Deadline.
     *
     * @return String of by parameter.
     */
    public String getBy() {
        return super.getParam();
    }

    @Override
    public void setDateTime(String line) {
        super.setDateTime(line);
    }

    /**
     * Returns String representation of Deadline.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        String datetime;
        if (hasDate && hasTime) {
            datetime = super.getDateTime();
        } else {
            datetime = param;
        }
        return "[D]" + super.toString() + " (by: " + datetime + ")";
    }
}