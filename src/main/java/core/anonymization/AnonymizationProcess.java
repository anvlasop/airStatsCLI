package core.anonymization;

import core.isolatotion.Purchase;

import java.util.*;
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
        Map<Float, Long> amountCountMap = allAirlineAmounts.stream().collect(Collectors.groupingBy(amount -> amount, Collectors.counting()));
        amountCountMap.values().removeIf(countOfAmounts -> countOfAmounts <= 5);
        reportAnonymizedCalculations(amountCountMap);
        extractStatisticalResults(amountCountMap.keySet());
    }

    private void reportAnonymizedCalculations(Map<Float,Long> amountCountMap) {
        amountCountMap.forEach((amount, count) -> System.out.println("| Airline purchase | " + amount + " | " + count + " |"));
    }

    private void extractStatisticalResults(Set<Float> amounts) {
        Float minAmount = Collections.min(amounts);
        Float maxAmount = Collections.max(amounts);
        Double averageAmount = extractAverage(amounts);
        Float medianAmount = calculateMedianAmount(amounts);

        reportStatisticalResults(minAmount, maxAmount, averageAmount, medianAmount);

    }

    private Double extractAverage(Set<Float> amounts) {
        OptionalDouble averageAmountOptional = amounts.stream().mapToDouble(amount -> amount).average();
        return averageAmountOptional.isPresent() ? averageAmountOptional.getAsDouble() : 0;
    }

    private Float calculateMedianAmount(Set<Float> amounts) {
        List<Float> amountList = amounts.stream().collect(Collectors.toList());
        double halfSize = amountList.size() / 2;
        Collections.sort(amountList);
        if (amountList.size() % 2 != 0) {
            int indexOfMedian = (int) Math.floor(halfSize);
            return (amountList.get(indexOfMedian));
        } else {
            int leftIndex = (int) Math.floor(halfSize);
            int rightIndex = (int) Math.ceil(halfSize);
            return ( amountList.get(leftIndex) + amountList.get(rightIndex) ) / 2;

        }

    }

    private void reportStatisticalResults(Float minAmount, Float maxAmount, Double averageAmount, Float medianAmount) {
        System.out.println("min: " + minAmount);
        System.out.println("max: " + maxAmount);
        System.out.println("average: " + averageAmount);
        System.out.println("median:" + medianAmount);
    }
}
