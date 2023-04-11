import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class MonthlyReport {

    private final String monthName;
    private final Map<TransactionType, List<String>> itemsNamesByTransactionType;
    private final Map<String, Integer> itemQuantityByName;
    private final Map<String, Integer> itemCostByName;

    public MonthlyReport(String monthName,
                         Map<TransactionType, List<String>> itemsNamesByTransactionType,
                         Map<String, Integer> itemsQuantityByName,
                         Map<String, Integer> itemsCostsByName
    ) {
        this.monthName = monthName;
        this.itemsNamesByTransactionType = itemsNamesByTransactionType;
        this.itemQuantityByName = itemsQuantityByName;
        this.itemCostByName = itemsCostsByName;
    }

    public String getMonthName() {
        return monthName;
    }

    public List<String> getItemsNamesByTransactionType(TransactionType transaction) {
        return itemsNamesByTransactionType.getOrDefault(transaction,
                                                        Collections.emptyList());
    }

    public int getItemQuantityByName(String name) {
        return itemQuantityByName.getOrDefault(name, 0);
    }

    public int getItemCostsByName(String name) {
        return itemCostByName.getOrDefault(name, 0);
    }

    public int getTotalSumOfMonthOperation(TransactionType transaction) {
        if (!itemsNamesByTransactionType.containsKey(transaction)) {
            System.out.println("Такой операции не производилось в этом месяце");
        }
        return itemsNamesByTransactionType.get(transaction)
                                          .stream()
                                          .mapToInt(name -> itemQuantityByName.get(name) * itemCostByName.get(name))
                                          .sum();
    }

    public void printMostProfitableItem() {
        if (!itemsNamesByTransactionType.containsKey(TransactionType.PROFIT)) {
            System.out.println("- В этом месяце не было прибыльных товаров");
            return;
        }
        Object[] objs = itemsNamesByTransactionType.get(TransactionType.PROFIT)
                                                   .stream()
                                                   .map(name -> {
                                                       return new Object[]
                                                        {name, itemQuantityByName.get(name) * itemCostByName.get(name)};
                                                   })
                                                   .max(Comparator.comparingInt(arr -> (Integer) arr[1]))
                                                   .get();
        System.out.printf("- Самый прибыльный товар за %s - это %s, доход от него составил %d\n",
                          monthName,
                          objs[0],
                          (Integer) objs[1]
        );
    }

    public void printMostExpensiveWaste() {
        if (!itemsNamesByTransactionType.containsKey(TransactionType.EXPENSE)) {
            System.out.println("- В этом месяце не было трат");
            return;
        }
        Object[] objs = itemsNamesByTransactionType.get(TransactionType.EXPENSE)
                                                   .stream()
                                                   .map(name -> {
                                                       return new Object[]
                                                        {name, itemQuantityByName.get(name) * itemCostByName.get(name)};
                                                   })
                                                   .max(Comparator.comparingInt(arr -> (Integer) arr[1]))
                                                   .get();

        System.out.printf("- Самая большая трата за %s, которая составила %d, - это %s\n",
                monthName,
                (Integer) objs[1],
                objs[0]
        );
    }

    public void printInfo() {
        System.out.println("- Название месяца:");
        System.out.println("\t" + monthName.toUpperCase());
        printMostProfitableItem();
        printMostExpensiveWaste();
    }

    @Override
    public String toString() {
        return "MonthlyReport{" +
                "monthName='" + monthName + '\'' +
                ", itemsNamesByTransactionType=" + itemsNamesByTransactionType +
                ", itemQuantityByName=" + itemQuantityByName +
                ", itemCostByName=" + itemCostByName +
                '}';
    }
}
