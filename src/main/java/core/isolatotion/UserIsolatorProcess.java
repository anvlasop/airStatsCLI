package core.isolatotion;

import com.fasterxml.jackson.databind.ObjectMapper;
import utils.PathReader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;

public class UserIsolatorProcess {
    public void startIsolation() {
        PathReader pathReader = new PathReader();
        File path = pathReader.readPath();
        boolean isDirectory = pathReader.checkIfIsDirectory(path);
        if (!isDirectory) {
            System.out.printf("The given path does not belong to a directory. \n" +
                    "Run this program again and define the right path");
        } else {
            extractUsersDataFromFile(path);
        }
    }

    private HashMap<Long, Purchases> extractUsersDataFromFile(File path) {

        HashMap<Long, Purchases> purchasesPerUser = new HashMap<>();
        File[] files = path.listFiles();
        List<File> fileList = Arrays.asList(files);
        fileList.forEach(file -> {
            try {
                String fileName = file.getName();
                Long userId = Long.parseLong(fileName.split("\\.")[0]);
                StringBuilder stringBuilder = new StringBuilder();
                Files.lines( Paths.get(file.getPath()), StandardCharsets.UTF_8).forEach(s ->
                        stringBuilder.append(s));

                Purchases purchases = extractPurchases(stringBuilder);
                purchasesPerUser.put(userId, purchases);
            } catch (NumberFormatException e) {
                System.out.println("The file '" + file.getName() + "' is not well named.");
            } catch (IOException e) {
                System.out.println("Error occurred during reading file.");
            }
        });

        return purchasesPerUser;

    }

    private Purchases extractPurchases(StringBuilder stringBuilder) throws com.fasterxml.jackson.core.JsonProcessingException {
        String userPurchasesString = stringBuilder.toString();
        ObjectMapper mapper = new ObjectMapper();
        Purchases purchases = mapper.readValue(userPurchasesString, Purchases.class);
        return purchases;
    }


}
