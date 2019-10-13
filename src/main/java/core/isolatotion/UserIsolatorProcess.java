package core.isolatotion;


import utils.PathReader;

import java.io.File;

public class UserIsolatorProcess {
    public void startIsolation() {
        PathReader pathReader = new PathReader();
        File path = pathReader.readPath();
        boolean isDirectory = pathReader.checkIfIsDirectory(path);
        if (!isDirectory) {
            System.out.printf("The given path does not belong to a directory. \n" +
                    "Run this program again and define the right path");
        } else {


        }



    }


}
