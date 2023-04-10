import java.util.Collections;
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

    public List<String> getItemsNamesByTransactionType(TransactionType type) {
        return itemsNamesByTransactionType.getOrDefault(type,
                                                        Collections.emptyList());
    }

    public int getItemQuantityByName(String name) {
        return itemQuantityByName.getOrDefault(name, 0);
    }

    public int getItemCostsByName(String name) {
        return itemCostByName.getOrDefault(name, 0);
    }
}
