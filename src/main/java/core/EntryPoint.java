package core;

import utils.ReadUserInput;

public class EntryPoint {

    public void start() {
        readPath();

    }

    private void readPath() {
        ReadUserInput readUserInput = new ReadUserInput();
        String folderPath = readUserInput.readUserConsoleInput();
    }






}
