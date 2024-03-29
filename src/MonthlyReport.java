import java.util.*;
import java.util.stream.Collectors;

public class MonthlyReport {

    private final String monthName;
    private final List<OperationRecord> operations;

    public MonthlyReport(String monthName) {
        this.monthName = monthName;
        this.operations = new ArrayList<>();
    }

    public String getMonthName() {
        return monthName;
    }

    public List<OperationRecord> getOperations() {
        return operations;
    }

    public void addNewOperation(String name, boolean isExpense, int quantity, int cost) {
        Objects.requireNonNull(name);

        OperationRecord operation = new OperationRecord(name, isExpense, quantity, cost);
        operations.add(operation);
    }

    public int getTotalSumOfMonthOperation(boolean isExpense) {
        return operations.stream()
                         .mapToInt(o -> o.isExpense() == isExpense ? o.getSum() : 0)
                         .sum();
    }

    public void printMostProfitableItem() {

        if (operations.isEmpty()) {
            System.out.println("- Нет доступных операций в этом месяце");
        }

        OperationRecord mostProfitableItem =
                operations.stream()
                          .filter(o -> !o.isExpense())
                          .sorted(Comparator.comparingInt(OperationRecord::getSum))
                          .collect(Collectors.toCollection(LinkedList::new))
                          .pollLast();


        System.out.printf("- Самый прибыльный товар за %s - это %s, доход от него составил %d\n",
                          monthName,
                          mostProfitableItem.getName(),
                          mostProfitableItem.getSum()
        );
    }

    public void printMostExpensiveWaste() {

        if (operations.isEmpty()) {
            System.out.println("- Нет доступных операций в этом месяце");
        }

        OperationRecord mostExpensiveWaste =
                operations.stream()
                          .filter(OperationRecord::isExpense)
                          .sorted(Comparator.comparingInt(OperationRecord::getSum))
                          .collect(Collectors.toCollection(LinkedList::new))
                          .pollLast();

        System.out.printf("- Самая большая трата за %s, которая составила %d, - это %s\n",
                monthName,
                mostExpensiveWaste.getSum(),
                mostExpensiveWaste.getName()
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
                ", operations=" + operations +
                '}';
    }
}
