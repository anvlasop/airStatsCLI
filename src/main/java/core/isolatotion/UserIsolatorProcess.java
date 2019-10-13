package core.isolatotion;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import utils.PathReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
                Integer userId = Integer.parseInt(fileName.split(".")[0]);
                List<String> linesOfFileContent = Files.readAllLines(file.toPath());
                String purchases = "";
                StringUtils.join(linesOfFileContent, purchases);
                JSONObject jsonObject = new JSONObject(purchases);
                //Continue here below:
                //ObjectMapper mapper = new ObjectMapper();

            } catch (IOException e) {
                System.out.println("Error occurred during reading file.");
            } catch (JSONException e) {
                System.out.println("Error while trying to parse file.");
            }



        });

    }


}
