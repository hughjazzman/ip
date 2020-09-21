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
        case FindCommand.COMMAND_WORD:
            return new FindCommand(line);
        default:
            throw new DukeException("Invalid Command!");
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
    public String[] parseTask(
            String descriptionParam, String command, String param, int paramPos)
            throws StringIndexOutOfBoundsException, DukeException {
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
}
