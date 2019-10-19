package core.anonymization;

import core.isolatotion.Purchase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnonymizationProcess {

    private ArrayList<Float> allAirlineAmounts = new ArrayList<>();

    public void collectAllAmounts(HashMap<Long,List<Purchase>> airlinePurchasesPerUser) {
        airlinePurchasesPerUser.forEach((userId, purchases) -> purchases.forEach(purchase -> {
            allAirlineAmounts.add(Float.parseFloat(purchase.getAmount()));
        }));
        calculateAmountCount();
    }

    private void calculateAmountCount() {
        Map<Object, Long> amountCountMap = allAirlineAmounts.stream().collect(Collectors.groupingBy(amount -> amount, Collectors.counting()));
    }
}
