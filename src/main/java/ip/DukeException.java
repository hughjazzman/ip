package ip;

public class DukeException extends Exception {

    private String message;

    public DukeException(String s) {
        this.message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
