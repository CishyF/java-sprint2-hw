import java.util.*;
import java.util.stream.Collectors;

public class YearlyReport implements Report {

    private final String year;
    private final List<MonthOperationsRecord> monthOperationsRecords;

    public YearlyReport(String year) {
        this.year = year;
        this.monthOperationsRecords = new ArrayList<>();
    }

    public void addNewMonthOperation(String monthName, int amount, boolean isExpense) {
        Objects.requireNonNull(monthName);

        MonthOperationsRecord operation = new MonthOperationsRecord(monthName, amount, isExpense);
        monthOperationsRecords.add(operation);
    }

    public List<MonthOperationsRecord> getMonthOperationsRecords() {
        return monthOperationsRecords;
    }

    public void printInfo() {
        System.out.println("- Год: " + year);
        System.out.println("- Прибыль по месяцам:");

        Set<String> alreadyPrinted = new HashSet<>();
        for (int i = 0; i < monthOperationsRecords.size(); i++) {

            var currentMonth = monthOperationsRecords.get(i);

            if (i < monthOperationsRecords.size() - 1 &&
                currentMonth.getMonthName().equals(monthOperationsRecords.get(i + 1).getMonthName())
            ) {
                var currentMonthWithAnotherTransaction = monthOperationsRecords.get(i + 1);

                alreadyPrinted.add(currentMonth.getMonthName());
                System.out.printf("\t%s: %d\n",
                        currentMonth.getMonthName(),
                        currentMonth.getEarningSum() + currentMonthWithAnotherTransaction.getEarningSum() -
                        currentMonth.getExpenseSum() - currentMonthWithAnotherTransaction.getExpenseSum()
                );
            } else if (!alreadyPrinted.contains(currentMonth.getMonthName())) {

                System.out.printf("\t%s: %d\n",
                        currentMonth.getMonthName(),
                        currentMonth.getEarningSum() - currentMonth.getExpenseSum()
                );
            }
        }

        System.out.println("- Средний расход за все месяцы в году:");
        List<MonthOperationsRecord> onlyExpenses = monthOperationsRecords.stream()
                                                            .filter(MonthOperationsRecord::isExpense)
                                                            .collect(Collectors.toList());
        System.out.printf("\t%d\n",
                (onlyExpenses.stream().mapToInt(MonthOperationsRecord::getExpenseSum).sum() / onlyExpenses.size())
        );

        System.out.println("- Средний доход за все месяцы в году:");
        List<MonthOperationsRecord> onlyEarning = monthOperationsRecords.stream()
                                                            .filter(o -> !o.isExpense())
                                                            .collect(Collectors.toList());
        System.out.printf("\t%d\n",
                (onlyEarning.stream().mapToInt(MonthOperationsRecord::getEarningSum).sum() / onlyEarning.size())
        );
    }

    @Override
    public String toString() {
        return "AnnualReport{" +
                "monthOperations=" + monthOperationsRecords +
                '}';
    }
}
