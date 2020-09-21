package ip.task;

public class Event extends Task{


    /**
     * Constructor
     *
     * @param description Description of Task.
     * @param at Timing of Task Event.
     */
    public Event(String description, String at) {
        super(description);
        this.param = at;
        setDateTime(at);
    }

    /**
     * Returns the by parameter of the Event.
     *
     * @return String of by parameter.
     */
    public String getAt() {
        return super.getParam();
    }

    @Override
    public void setDateTime(String line) {
        super.setDateTime(line);
    }

    /**
     * Returns String representation of Event.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        String datetime;
        if (hasDate && hasTime) {
            datetime = super.getDateTime();
        } else {
            datetime = at;
        }
        return "[E]" + super.toString() + " (at: " + datetime + ")";
    }
}
