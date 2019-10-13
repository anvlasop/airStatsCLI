package utils;

import java.util.Scanner;

public class ReadUserInput {


    public void readUserConsoleInput() {
        System.out.println("Program starts...");
        System.out.println("Please define folder's path for user files:");

        Scanner scanner = new Scanner(System.in);
        String userInputFolderPath = scanner.nextLine();

        System.out.println(userInputFolderPath);

    }
}
