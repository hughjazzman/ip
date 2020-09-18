package ip;

import ip.command.Command;
import ip.file.FileManager;
import ip.parser.Parser;
import ip.task.Task;
import ip.task.TaskManager;
import ip.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {
    /** File path **/
    private static final String ROOT = System.getProperty("user.dir");
    private static final String FILE_NAME = "data.txt";
    // inserts correct file path separator to data.txt file
    private static final Path FILE_PATH = Paths.get(ROOT, "src", "main", "resources", FILE_NAME);
    private static final Path DIR_PATH = Paths.get(ROOT, "src", "main", "resources");
    private static final boolean DIR_EXISTS = Files.exists(DIR_PATH);

    private final Ui ui;
    private TaskManager taskManager;

    /**
     * Constructor.
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
            taskManager = createTaskManager(fileManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Returns TaskManager object given an input FileManager.
     *
     * @param fileManager FileManager of a file.
     * @return TaskManager object to keep track of tasks.
     * @throws IOException If an I/O error occurs.
     */
    private TaskManager createTaskManager(FileManager fileManager) throws IOException {

        // Will loop as long as FileNotFoundException is caught
        while (true) {
            try {
                return new TaskManager(fileManager);
            } catch (FileNotFoundException e) {
                ui.printFileNotFound();
                // Create file if not found
                try {
                    fileManager.createFile();
                } catch (IOException err) {
                    ui.printFileError();
                    throw err;
                }
            } catch (IOException e) {
                ui.printFileError();
                throw e;
            }
        }
    }

}
