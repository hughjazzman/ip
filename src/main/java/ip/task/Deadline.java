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
        setDateTime(by);
    }

    public String getBy() {
        return by;
    }

    @Override
    public void setDateTime(String line) {
        super.setDateTime(line);
    }

    @Override
    public String toString() {
        String datetime;
        if (hasDate && hasTime) {
            datetime = super.getDateTime();
        } else {
            datetime = by;
        }
        return "[D]" + super.toString() + " (by: " + datetime + ")";
    }
}