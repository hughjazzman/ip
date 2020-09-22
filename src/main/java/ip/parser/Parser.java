package ip.parser;

import ip.DukeException;
import ip.commands.AddCommand;
import ip.commands.Command;
import ip.commands.DeleteCommand;
import ip.commands.DoneCommand;
import ip.commands.ExitCommand;
import ip.commands.FindCommand;
import ip.commands.ListCommand;
import ip.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final Ui ui = new Ui();

    /**
     * Returns a Command object based on the input line.
     *
     * @param line Line of user input.
     * @return Command object.
     * @throws DukeException If given line is blank.
     */
    public static Command parse(String line) throws DukeException {
        // Prevent blank tasks
        if (line.isBlank()) {
            throw new DukeException("Invalid command!");
        }
        // Find position of first space
        int spacePos = line.indexOf(" ");
        // command is first word
        String commandWord = spacePos > 0 ? line.substring(0, spacePos).strip() : line;
        String num;

        switch (commandWord) {
        case AddCommand.COMMAND_TODO: // Fallthrough
        case AddCommand.COMMAND_DEADLINE: // Fallthrough
        case AddCommand.COMMAND_EVENT:
            return prepareAdd(commandWord, line);
        case DeleteCommand.COMMAND_WORD: // Fallthrough
        case DoneCommand.COMMAND_WORD:
            num = line.substring(spacePos + 1).strip();
            return prepareIndex(commandWord, num);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case FindCommand.COMMAND_WORD:
            String desc = prepareDesc(commandWord, line);
            return new FindCommand(desc);
        default:
            throw new DukeException("Invalid Command!");
        }
    }

    /**
     * Returns AddCommand object after parsing command to add Task.
     *
     * @param command Task to be added.
     * @param line Line of user input.
     * @return AddCommand object to be executed.
     * @throws DukeException If the description/param is blank.
     */
    private static AddCommand prepareAdd(String command, String line) throws DukeException {
        // Position of task description, /by marker, and /at marker
        int byPos, atPos;
        String descriptionParam, description;

        // Check for blank description
        descriptionParam = prepareDesc(command, line);

        String by, at;
        String[] details;

        switch (command) {
        case AddCommand.COMMAND_TODO:
            return new AddCommand(command, descriptionParam);
        case AddCommand.COMMAND_DEADLINE:
            byPos = descriptionParam.indexOf(AddCommand.PARAM_BY);

            details = parseTask(descriptionParam, command, AddCommand.PARAM_BY, byPos);
            by = details[0];
            description = details[1];

            return new AddCommand(command, description, by);
        case AddCommand.COMMAND_EVENT:
            atPos = descriptionParam.indexOf(AddCommand.PARAM_AT);

            details = parseTask(descriptionParam, command, AddCommand.PARAM_AT, atPos);
            at = details[0];
            description = details[1];

            return new AddCommand(command, description, at);
        default:
            throw new DukeException("Invalid Command!");
        }
    }

    /**
     * Returns Command object after parsing command that deals with task index.
     *
     * @param command Command to be executed.
     * @param num Task index number.
     * @return Command object.
     * @throws DukeException If the number is in a wrong format, or task index does not exist.
     */
    private static Command prepareIndex(String command, String num) throws DukeException {
        int id;

        try {
            id = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            ui.printWrongFormatInteger();
            throw new DukeException("");
        }

        switch (command) {
        case "delete":
            return new DeleteCommand(id);
        case "done":
            return new DoneCommand(id);
        default:
            throw new DukeException("Invalid Command!");
        }
    }

    /**
     * Returns parsed description of a command.
     *
     * @param command Command to be executed.
     * @param line Line of user input.
     * @return String of parsed description.
     * @throws DukeException If the description is blank.
     */
    private static String prepareDesc(String command, String line) throws DukeException {
        // Position of find description
        int descPos = line.indexOf(" ");
        String description;

        // Check for blank description
        try {
            description = line.substring(descPos).strip();
        } catch (StringIndexOutOfBoundsException e) {
            ui.printEmpty(command);
            throw new DukeException("");
        }
        return description.strip();
    }

    /**
     * Parses the parameters of the user input. Used in parseTask.
     *
     * @param descriptionParam User input after the command.
     * @param command Command name.
     * @param param Parameter name.
     * @param paramPos Parameter index in descriptionParam.
     * @return paramDetails User input for the param.
     * @throws DukeException If paramPos is < 0 (missing param)
     * @throws StringIndexOutOfBoundsException If paramPos is > descriptionParam.length() (blank param)
     */
    private static String parseParam(
            String descriptionParam, String command, String param, int paramPos)
            throws DukeException {
        // Check that there is a parameter
        if (paramPos < 0) {
            ui.printWrongFormatTask(command, param);
            throw new DukeException("Missing parameter");
        }

        String paramDetails;
        // Check for blank parameter
        try {
            paramDetails = descriptionParam.substring(paramPos + param.length() + 1).strip();
            return paramDetails;
        } catch (StringIndexOutOfBoundsException e) {
            ui.printEmpty(command + " " + param + " parameter");
            throw new DukeException("");
        }
    }

    /**
     * Parses the description of the user input. Used in parseTask.
     *
     * @param descriptionParam User input after the command.
     * @param command Command name.
     * @param paramPos Parameter index in descriptionParam.
     * @return description User input for the command description.
     * @throws DukeException If paramPos <= 0 (missing description)
     */
    private static String parseDesc(
            String descriptionParam, String command, int paramPos)
            throws DukeException {
        String description;
        try {
            description = descriptionParam.substring(0, paramPos - 1).strip();
            return description;
        } catch (StringIndexOutOfBoundsException e) {
            ui.printEmpty(command);
            throw new DukeException("");
        }
    }

    /**
     * Parses the entire task given as user input with description and parameters.
     * Used in execAddTask.
     *
     * @param descriptionParam User input after the command.
     * @param command Command name.
     * @param param Parameter name.
     * @param paramPos Parameter index in descriptionParam.
     * @return details String array of paramDetails and description of task.
     * @throws StringIndexOutOfBoundsException If parsed parameter field is blank.
     * @throws DukeException If parsed description is blank.
     */
    private static String[] parseTask(
            String descriptionParam, String command, String param, int paramPos)
            throws DukeException {
        String paramDetails, description;
        String[] details = new String[2];

        paramDetails = parseParam(descriptionParam, command, param, paramPos);
        description = parseDesc(descriptionParam, command, paramPos);

        if (description.isBlank()) {
            ui.printEmpty(command);
            throw new DukeException("Missing description");
        }

        details[0] = paramDetails;
        details[1] = description;

        return details;

    }


    /**
     * Returns a LocalDate object parsed from an input line.
     *
     * @param line Line of user input.
     * @return LocalDate object parsed.
     */
    public static LocalDate parseDate(String line) {
        String[] params = line.split("\\s+");
        LocalDate date = null;
        for (String s : params) {
            try {
                date = LocalDate.parse(s);
            } catch (DateTimeParseException ignored) {
            }
        }
        return date;
    }

    /**
     * Returns a LocalTime object parsed from an input line.
     *
     * @param line Line of user input.
     * @return LocalTime object parsed.
     */
    public static LocalTime parseTime(String line) {
        String[] params = line.split("\\s+");
        LocalTime time = null;
        for (String s : params) {
            try {
                time = LocalTime.parse(s);
            } catch (DateTimeParseException ignored) {
            }

        }
        return time;
    }

}
