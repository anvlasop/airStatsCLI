package core.isolatotion;

import com.fasterxml.jackson.databind.ObjectMapper;
import utils.PathReader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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

        ArrayList<UserPurchases> purchasesPerUserList = new ArrayList<>();
        File[] files = path.listFiles();
        List<File> fileList = Arrays.asList(files);
        fileList.forEach(file -> {
            try {
                String fileName = file.getName();
                Long userId = Long.parseLong(fileName.split("\\.")[0]);
                StringBuilder stringBuilder = new StringBuilder();
                Files.lines( Paths.get(file.getPath()), StandardCharsets.UTF_8).forEach(s ->
                        stringBuilder.append(s));

                UserPurchases userPurchases = getUserPurchases(userId, stringBuilder);
                purchasesPerUserList.add(userPurchases);
            } catch (IOException e) {
                System.out.println("Error occurred during reading file.");
            } catch (Exception e) {
                System.out.println("Error while trying to parse file.");
            }

            System.out.println("ce fini!");
        });

    }

    private UserPurchases getUserPurchases(Long userId, StringBuilder stringBuilder) throws com.fasterxml.jackson.core.JsonProcessingException {
        String userPurchasesString = stringBuilder.toString();
        ObjectMapper mapper = new ObjectMapper();
        Purchases purchases = mapper.readValue(userPurchasesString, Purchases.class);
        UserPurchases userPurchases = new UserPurchases();
        userPurchases.setPurchases(purchases);
        userPurchases.setUserId(userId);
        return userPurchases;
    }


}
