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

    /**
     * Sets the date of the Task.
     *
     * @param date LocalDate object.
     */
    private void setDate(LocalDate date) {
        this.date = date;
        hasDate = true;
    }

    /**
     * Sets the time of the Task.
     *
     * @param time LocalTime object.
     */
    private void setTime(LocalTime time) {
        this.time = time;
        hasTime = true;
    }

    /**
     * Sets the date and time of the Task given user input.
     *
     * @param line Line of user input with date and/or time.
     */
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

    /**
     * Returns a String with formatted date of Task.
     *
     * @param format Format of date desired.
     * @return String formatted date.
     */
    private String getDateFormat(String format) {
        return hasDate ? date.format(DateTimeFormatter.ofPattern(format)) : "";
    }

    /**
     * Returns a String with formatted time of Task.
     *
     * @return String formatted time to ISO standard.
     */
    private String getTimeFormat() {
        return hasTime ? time.format(DateTimeFormatter.ISO_LOCAL_TIME) : "";
    }

    /**
     * Returns a String with date and time of Task.
     *
     * @return String of date and time separated by space.
     */
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
