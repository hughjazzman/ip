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

    public String getBy() {
        return super.getParam();
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