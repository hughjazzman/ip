package ip.file;

import ip.task.TaskManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;

public class FileManager {
    private String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    /**
     * Creates a file at filePath.
     *
     * @throws IOException If an I/O error occurs.
     */
    // @@author hughjazzman-reused
    // Reused from https://www.javatpoint.com/how-to-create-a-file-in-java with minor modifications
    public void createFile() throws IOException {
        File file = new File(filePath);
        try {
            if (file.createNewFile()) {
                System.out.println("File created at location: " + file.getCanonicalPath());
            } else {
                System.out.println("File already exists at location: " + file.getCanonicalPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

    }
    // @@author

    /**
     * Creates a directory at filePath.
     *
     * @param dirPath Path to directory to be created.
     * @throws IOException If an I/O error occurs.
     */
    public static void createDirectory(String dirPath) throws IOException {
        // Solution adapted from https://www.javatpoint.com/how-to-create-a-file-in-java
        File file = new File(dirPath);
        try {
            if (file.mkdir()) {
                System.out.println("Directory created at location: " + file.getCanonicalPath());
            } else {
                System.out.println("Directory already exists at location: " + file.getCanonicalPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Parses the file from the given filePath.
     *
     * @param filePath Path to the file as a string.
     * @throws IOException If an I/O error occurs.
     */
    // @@author hughjazzman-reused
    // Reused from https://stackoverflow.com/a/45826710 with minor modifications
    public void getLines(TaskManager taskManager, String filePath) throws IOException {
        FileInputStream stream;
        try {
            stream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        try {
            taskManager.parseLines(reader);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
    // @@author
}
