import java.util.*;
import java.util.stream.Collectors;

public class YearlyReport {

    private final String year;
    private final List<MonthOperationsRecord> monthOperationsRecords;

    public YearlyReport(String year) {
        this.year = year;
        this.monthOperationsRecords = new ArrayList<>();
    }

    public void addNewMonthOperations(MonthOperationsRecord record) {
        Objects.requireNonNull(record);

        monthOperationsRecords.add(record);
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
        List<Integer> onlyExpenses = monthOperationsRecords.stream()
                                                            .map(MonthOperationsRecord::getExpenseSum)
                                                            .collect(Collectors.toList());
        System.out.printf("\t%d\n", (onlyExpenses.stream().mapToInt(i -> i).sum() / onlyExpenses.size()));

        System.out.println("- Средний доход за все месяцы в году:");
        List<Integer> onlyEarning = monthOperationsRecords.stream()
                                                          .map(MonthOperationsRecord::getEarningSum)
                                                          .collect(Collectors.toList());
        System.out.printf("\t%d\n", (onlyEarning.stream().mapToInt(i -> i).sum() / onlyExpenses.size()));
    }

    @Override
    public String toString() {
        return "AnnualReport{" +
                "monthOperations=" + monthOperationsRecords +
                '}';
    }
}
