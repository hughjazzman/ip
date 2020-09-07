package ip.task;

public class Event extends Task{

    protected String at;

    /**
     * Constructor
     *
     * @param description Description of Task.
     * @param at Timing of Task Event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
