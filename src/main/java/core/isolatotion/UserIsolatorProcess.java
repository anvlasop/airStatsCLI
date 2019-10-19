package core.isolatotion;

import com.fasterxml.jackson.databind.ObjectMapper;
import utils.PathReader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class UserIsolatorProcess {

    private static final String AIRLINE_PURCHASE_TYPE = "airline";
    private HashMap<Long, Purchases> allPurchasesPerUser;
    /**
     * The key of this Map represents user id as extracted
      */
    private HashMap<Long, List<Purchase>> airlinePurchasesPerUser = new HashMap<>();

    public void startIsolation() {
        PathReader pathReader = new PathReader();
        File path = pathReader.readPath();
        boolean isDirectory = pathReader.checkIfIsDirectory(path);
        if (!isDirectory) {
            System.out.println("The given path does not belong to a directory. \n" +
                    "Run this program again and define the right path");
            return;
        } else {
            extractUsersDataFromFile(path);
            allPurchasesPerUser.forEach((userId, purchases) -> {
                List<Purchase> purchasesList =  purchases.getPurchases().stream().filter(purchase ->
                        purchase.getType().equals(AIRLINE_PURCHASE_TYPE)).collect(Collectors.toList());
                airlinePurchasesPerUser.put(userId, purchasesList);
            });
//            airlinePurchasesPerUser.forEach();
        }

    }

    private void extractUsersDataFromFile(File path) {

        allPurchasesPerUser = new HashMap<>();
        File[] files = path.listFiles();
        List<File> fileList = Arrays.asList(files);
        fileList.forEach(file -> {
            try {
                String fileName = file.getName();
                Long userId = Long.parseLong(fileName.split("\\.")[0]);
                StringBuilder stringBuilder = new StringBuilder();
                Files.lines( Paths.get(file.getPath()), StandardCharsets.UTF_8).forEach(s -> stringBuilder.append(s));

                Purchases purchases = extractPurchases(stringBuilder);
                allPurchasesPerUser.put(userId, purchases);
            } catch (NumberFormatException e) {
                System.out.println("The file '" + file.getName() + "' is not well named.");
            } catch (IOException e) {
                System.out.println("Error occurred during reading file.");
            }
        });
    }

    private Purchases extractPurchases(StringBuilder stringBuilder) throws com.fasterxml.jackson.core.JsonProcessingException {
        String userPurchasesString = stringBuilder.toString();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(userPurchasesString, Purchases.class);
    }


}
