package core.isolatotion;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import utils.PathReader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class UserIsolatorProcess {
    public void startIsolation() {
        PathReader pathReader = new PathReader();
        File path = pathReader.readPath();
        boolean isDirectory = pathReader.checkIfIsDirectory(path);
        if (!isDirectory) {
            System.out.printf("The given path does not belong to a directory. \n" +
                    "Run this program again and define the right path");
        } else {
            extractUserDataFromFile(path);
        }
    }

    private void extractUserDataFromFile(File path) {

        File[] files = path.listFiles();
        List<File> fileList = Arrays.asList(files);
        fileList.stream().forEach(file -> {
            try {
                String fileName = file.getName();
                //below have problem
                Integer userId = Integer.parseInt(fileName.split("\\.")[0]);
                StringBuilder stringBuilder = new StringBuilder();
                Files.lines( Paths.get(file.getPath()), StandardCharsets.UTF_8).forEach(s ->
                        stringBuilder.append(s).append("\n"));
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                //Continue here below:


            } catch (IOException e) {
                System.out.println("Error occurred during reading file.");
            } catch (Exception e) {
                System.out.println("Error while trying to parse file.");
            }



        });

    }


}
