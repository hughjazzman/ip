package ip.parser;
import ip.DukeException;
import ip.command.*;
import ip.ui.Ui;

public class Parser {


    public static Command parse(String line) throws DukeException {
        // Prevent blank tasks
        if (line.isBlank()) {
            throw new DukeException();
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
            return new AddCommand(commandWord, line);
        case DeleteCommand.COMMAND_WORD:
            num = line.substring(spacePos + 1).strip();
            return new DeleteCommand(num);
        case DoneCommand.COMMAND_WORD:
            num = line.substring(spacePos + 1).strip();
            return new DoneCommand(num);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            throw new DukeException();
        }
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
            throws StringIndexOutOfBoundsException, DukeException {
        Ui ui = new Ui();
        // Check that there is a parameter
        if (paramPos < 0) {
            ui.printWrongFormatTask(command, param);
            throw new DukeException();
        }

        String paramDetails;
        // Check for blank parameter
        try {
            paramDetails = descriptionParam.substring(paramPos + param.length() + 1).strip();
            return paramDetails;
        } catch (StringIndexOutOfBoundsException e) {
            ui.printEmpty(command + " " + param + " parameter");
            throw e;
        }
    }

    /**
     * Parses the description of the user input. Used in parseTask.
     *
     * @param descriptionParam User input after the command.
     * @param command Command name.
     * @param paramPos Parameter index in descriptionParam.
     * @return description User input for the command description.
     * @throws StringIndexOutOfBoundsException If paramPos <= 0 (missing description)
     */
    private static String parseDesc(
            String descriptionParam, String command, int paramPos)
            throws StringIndexOutOfBoundsException {
        Ui ui = new Ui();
        String description;
        try {
            description = descriptionParam.substring(0, paramPos - 1).strip();
            return description;
        } catch (StringIndexOutOfBoundsException e) {
            ui.printEmpty(command);
            throw e;
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
    public static String[] parseTask(
            String descriptionParam, String command, String param, int paramPos)
            throws StringIndexOutOfBoundsException, DukeException {
        Ui ui = new Ui();
        String paramDetails, description;
        String[] details = new String[2];

        paramDetails = parseParam(descriptionParam, command, param, paramPos);
        description = parseDesc(descriptionParam, command, paramPos);

        if (description.isBlank()) {
            ui.printEmpty(command);
            throw new DukeException();
        }

        details[0] = paramDetails;
        details[1] = description;

        return details;

    }
}
