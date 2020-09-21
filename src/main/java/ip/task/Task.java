package ip.task;

import ip.parser.Parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task {

    protected String description;

    protected String param;
    protected boolean isDone;

    protected LocalDate date;
    protected LocalTime time;
    protected boolean hasDate = false;
    protected boolean hasTime = false;

    /**
     * Constructor
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns description of the Task.
     *
     * @return String description of Task.
     */
    public String getDescription() {
        return description;
    }

    
    protected String getParam() {
        return param;
    }

    /**
     * Marks a Task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns a boolean indicating if a Task is done.
     *
     * @return boolean if Task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    private void setDate(LocalDate date) {
        this.date = date;
        hasDate = true;
    }

    private void setTime(LocalTime time) {
        this.time = time;
        hasTime = true;
    }

    protected void setDateTime(String line) {
        LocalDate parsedDate = Parser.parseDate(line);
        LocalTime parsedTime = Parser.parseTime(line);
        if (parsedDate != null) {
            setDate(parsedDate);
        }
        if (parsedTime != null) {
            setTime(parsedTime);
        }
    }

    private String getDateFormat(String format) {
        return hasDate ? date.format(DateTimeFormatter.ofPattern(format)) : "";
    }

    private String getTimeFormat() {
        return hasTime ? time.format(DateTimeFormatter.ISO_LOCAL_TIME) : "";
    }

    protected String getDateTime() {
        String date = getDateFormat("MMM d yyyy");
        String time = getTimeFormat();
        return date + " " + time;
    }

    /**
     * Returns a status icon depending on whether task isDone.
     *
     * @return Tick symbol if isDone else X symbol.
     */
    public String getStatusIcon() {
        return isDone ? "[/]" : "[X]"; //return tick or X symbols
    }

    /**
     * Returns a String representation of the Task, using the statusIcon and description.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}
