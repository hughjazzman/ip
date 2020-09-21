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
    }

    public String getAt() {
        return super.getParam();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + param + ")";
    }
}
