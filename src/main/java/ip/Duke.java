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
    private static final String root = System.getProperty("user.dir");
    // inserts correct file path separator to data.txt file
    private static final Path filePath = Paths.get(root, "src", "main", "resources", "data.txt");
    private static final Path dirPath = Paths.get(root, "src", "main", "resources");
    private static final boolean directoryExists = Files.exists(dirPath);
    /** Number of dashes used in printed horizontal line **/
    private static final int NUM_DASHES = 60;



    private Ui ui;
    private FileManager fileManager;
    private TaskManager taskManager;

    public Duke(String filepath) {
        if (directoryExists) {
            fileManager = new FileManager(filePath.toString());
        } else {
            fileManager = new FileManager("data.txt");
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
//        // Print logo and greeting
//        printLogo();
//        printGreeting();
        new Duke(filePath.toString()).run();
//        FileManager fileManager;
//        if (directoryExists) {
//            fileManager = new FileManager(filePath.toString());
//        } else {
//            fileManager = new FileManager("data.txt");
//        }
//
//        // Create TaskManager
//        TaskManager taskManager;
//        try {
//            taskManager = createTaskManager(fileManager);
//        } catch (IOException e) {
//            return;
//        }
//
//
//        // Scanner class for user input
//        String line;
//        Scanner in = new Scanner(System.in);
//
//        // Parsing of user input
//        do {
//            line = in.nextLine().strip();
//            execute(taskManager, line);
//        } while (!line.equals(COMMAND_BYE));
//
//        // Exit program
//        printFarewell();
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
