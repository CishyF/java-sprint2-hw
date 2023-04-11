import java.util.*;
import java.util.stream.Collectors;

public class AnnualReport {

    private final String year;
    private final List<MonthOperations> monthOperations;

    public AnnualReport(String path) {
        year = path.split("\\.")[1];
        monthOperations = new ArrayList<>();
    }

    public void addNewMonthOperation(String monthName, int amount, TransactionType transaction) {
        Objects.requireNonNull(monthName);

        MonthOperations operation = new MonthOperations(monthName, amount, transaction);
        monthOperations.add(operation);
    }

    public List<MonthOperations> getMonthOperations() {
        return monthOperations;
    }

    public void printInfo() {
        System.out.println("- Год: " + year);
        System.out.println("- Прибыль по месяцам:");

        Set<String> alreadyPrinted = new HashSet<>();
        for (int i = 0; i < monthOperations.size(); i++) {

            if (i < monthOperations.size() - 1 &&
                monthOperations.get(i).getMonthName().equals(monthOperations.get(i + 1).getMonthName())
            ) {
                alreadyPrinted.add(monthOperations.get(i).getMonthName());
                System.out.print("\t" + monthOperations.get(i).getMonthName());
                System.out.printf(": %d\n",
                                  monthOperations.get(i).getTransactionType().isExpense()
                                        ? monthOperations.get(i + 1).getAmount() - monthOperations.get(i).getAmount()
                                        : monthOperations.get(i).getAmount() - monthOperations.get(i + 1).getAmount()
                );
            } else if (!alreadyPrinted.contains(monthOperations.get(i).getMonthName())) {
                System.out.print("\t" + monthOperations.get(i).getMonthName());
                System.out.printf(": %d\n",
                                  monthOperations.get(i).getTransactionType().isExpense()
                                        ? -1 * monthOperations.get(i).getAmount()
                                        : monthOperations.get(i).getAmount()
                );
            }
        }

        System.out.println("- Средний расход за все месяцы в году:");
        List<MonthOperations> onlyExpenses = monthOperations.stream()
                                                            .filter(o -> o.getTransactionType().isExpense())
                                                            .collect(Collectors.toList());
        System.out.println("\t" + (onlyExpenses.stream().mapToInt(MonthOperations::getAmount).sum() / onlyExpenses.size()));

        System.out.println("- Средний доход за все месяцы в году:");
        List<MonthOperations> onlyProfit = monthOperations.stream()
                                                          .filter(o -> !o.getTransactionType().isExpense())
                                                          .collect(Collectors.toList());
        System.out.println("\t" + (onlyProfit.stream().mapToInt(MonthOperations::getAmount).sum() / onlyProfit.size()));
    }

    @Override
    public String toString() {
        return "AnnualReport{" +
                "monthOperations=" + monthOperations +
                '}';
    }
}
