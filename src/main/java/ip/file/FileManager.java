package ip.file;

import ip.DukeException;
import ip.task.TaskManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileManager {
    private final String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    /**
     * Creates a file at filePath.
     *
     * @throws DukeException If an I/O error occurs.
     */
    // @@author hughjazzman-reused
    // Reused from https://www.javatpoint.com/how-to-create-a-file-in-java with minor modifications
    public void createFile() throws DukeException {
        File file = new File(filePath);
        try {
            if (file.createNewFile()) {
                // Commented out until testing file creation can be done separately from text-ui-test
                //System.out.println("File created at location: " + file.getCanonicalPath());
                System.out.println("File created at location: ");
            } else {
                System.out.println("File already exists at location: " + file.getCanonicalPath());
            }
        } catch (IOException e) {
            throw new DukeException("IO Error");
        }

    }
    // @@author

    /**
     * Creates a directory at filePath.
     *
     * @param dirPath Path to directory to be created.
     * @throws DukeException If an I/O error occurs.
     */
    public static void createDirectory(String dirPath) throws DukeException {
        // Solution adapted from https://www.javatpoint.com/how-to-create-a-file-in-java
        File file = new File(dirPath);
        try {
            if (file.mkdir()) {
                System.out.println("Directory created at location: " + file.getCanonicalPath());
            } else {
                System.out.println("Directory already exists at location: " + file.getCanonicalPath());
            }
        } catch (IOException e) {
            throw new DukeException("IO Error");
        }
    }

    /**
     * Parses the file from the given filePath.
     *
     * @throws DukeException If an I/O error occurs.
     * @throws FileNotFoundException If the file does not exist at filePath.
     */
    // @@author hughjazzman-reused
    // Reused from https://stackoverflow.com/a/45826710 with minor modifications
    public void getLines(TaskManager taskManager) throws DukeException, FileNotFoundException {
        FileInputStream stream;

        stream = new FileInputStream(filePath);

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        try {
            taskManager.parseLines(reader);
        } catch (IOException e) {
            throw new DukeException("IO Error");
        }
        try {
            reader.close();
        } catch (IOException e) {
            throw new DukeException("IO Error");
        }
    }
    // @@author

    /**
     * Write the lines as input to the file.
     *
     * @param lines Input data being written to file.
     * @throws DukeException If an I/O error occurs.
     */
    // @@author hughjazzman-reused
    // Reused from https://www.journaldev.com/878/java-write-to-file#java-write-to-file-example with minor modifications
    public void writeFile(String lines) throws DukeException {
        File file = new File(filePath);
        try (FileWriter fr = new FileWriter(file)) {
            fr.write(lines);
        } catch (IOException e) {
            throw new DukeException("IO Error");
        }
        // close resources
    }
    // @@author
}
