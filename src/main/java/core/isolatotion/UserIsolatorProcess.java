package core.isolatotion;

import com.fasterxml.jackson.databind.ObjectMapper;
import utils.PathReader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.OptionalDouble;

public class UserIsolatorProcess {

    private static final String TYPE_AIRLINE = "airline";
    private HashMap<Long, Purchases> purchasesPerUser;

    public void startIsolation() {
        PathReader pathReader = new PathReader();
        File path = pathReader.readPath();
        boolean isDirectory = pathReader.checkIfIsDirectory(path);
        if (!isDirectory) {
            System.out.println("The given path does not belong to a directory. \n" +
                    "Run this program again and define the right path");
        } else {
            extractUsersDataFromFile(path);
            purchasesPerUser.forEach((aLong, purchases) -> {
                OptionalDouble minAmount = purchases.getPurchases().stream().filter(purchase -> purchase.getType().equals(TYPE_AIRLINE)).mapToDouble(purchase -> Float.parseFloat(purchase.getAmount())).min();
                OptionalDouble maxAmount = purchases.getPurchases().stream().filter(purchase -> purchase.getType().equals(TYPE_AIRLINE)).mapToDouble(purchase -> Float.parseFloat(purchase.getAmount())).max();
                OptionalDouble averageAmount = purchases.getPurchases().stream().filter(purchase -> purchase.getType().equals(TYPE_AIRLINE)).mapToDouble(purchase -> Float.parseFloat(purchase.getAmount())).average();
                System.out.println(aLong);
                System.out.println(minAmount);
                System.out.println(maxAmount);
                System.out.println(averageAmount);
            });

            System.out.println("sdf");

        }
    }

    private void extractUsersDataFromFile(File path) {

        purchasesPerUser = new HashMap<>();
        File[] files = path.listFiles();
        List<File> fileList = Arrays.asList(files);
        fileList.forEach(file -> {
            try {
                String fileName = file.getName();
                Long userId = Long.parseLong(fileName.split("\\.")[0]);
                StringBuilder stringBuilder = new StringBuilder();
                Files.lines( Paths.get(file.getPath()), StandardCharsets.UTF_8).forEach(s -> stringBuilder.append(s));

                Purchases purchases = extractPurchases(stringBuilder);
                purchasesPerUser.put(userId, purchases);
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
