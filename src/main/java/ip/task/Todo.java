package ip.task;

public class Todo extends Task{
    /**
     * Constructor.
     *
     * @param description Description of Todo Task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * String representation of Todo.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
