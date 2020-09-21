package ip;

import ip.commands.Command;
import ip.file.FileManager;
import ip.parser.Parser;
import ip.task.TaskManager;
import ip.ui.Ui;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    /** File path **/
    private static final String ROOT = System.getProperty("user.dir");
    private static final String FILE_NAME = "data.txt";
    // inserts correct file path separator to data.txt file
    private static final Path FILE_PATH = Paths.get(ROOT, "src", "main", "resources", FILE_NAME);
    private static final Path DIR_PATH = Paths.get(ROOT, "src", "main", "resources");
    private static final boolean DIR_EXISTS = Files.exists(DIR_PATH);
    /** Instances of class objects for UI and task management **/
    private final Ui ui;
    private TaskManager taskManager;

    /**
     * Constructor.
     * Manages file and storage parsing.
     */
    public Duke() {
        // Uses a data.txt file located at src/main/resources if available
        FileManager fileManager;
        if (DIR_EXISTS) {
            fileManager = new FileManager(FILE_PATH.toString());
        } else { // otherwise just create a data.txt file at the same location as this file
            fileManager = new FileManager(FILE_NAME);
        }
        ui = new Ui();
        try {
            taskManager = TaskManager.createTaskManager(fileManager, ui);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Runs Duke.
     * Handles commands and UI.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printHorizontalLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskManager, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printHorizontalLine();
            }
        }
    }

    /**
     * Runs an instance of the Duke application.
     *
     * @param args Command line arguments (if any).
     */
    public static void main(String[] args) {
        new Duke().run();
    }

}
