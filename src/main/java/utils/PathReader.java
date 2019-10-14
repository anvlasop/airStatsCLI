package utils;

import java.io.File;
import java.util.Scanner;

public class PathReader {

    public File readPath() {
        System.out.println("Define directory's absolute path:");
        Scanner scanner = new Scanner(System.in);
        String folderPathString = scanner.nextLine();
        System.out.println(folderPathString);

        return new File(folderPathString);
    }

    public boolean checkIfIsDirectory(File path) {
        return path.isDirectory();
    }
}
