package ip;

public class DukeException extends Exception {

    private final String message;

    public DukeException(String s) {
        this.message = s;
    }

    /**
     * Returns message in Exception as a String.
     *
     * @return String message for the Exception.
     */
    @Override
    public String getMessage() {
        return message;
    }
}
