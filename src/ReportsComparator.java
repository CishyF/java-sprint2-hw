import java.util.Iterator;
import java.util.List;

public class ReportsComparator {

    private ReportsComparator() {}

    public static void compareReports(List<MonthlyReport> monthlyReports, AnnualReport annualReport) {
        Iterator<MonthOperations> operationsIterator = annualReport.getMonthOperations().iterator();

        for (float monthNumber = 0; operationsIterator.hasNext(); monthNumber += 0.5) {

            MonthOperations operation = operationsIterator.next();

            if (operation.getAmount() != monthlyReports.get((int) monthNumber)
                                                        .getTotalSumOfMonthOperation(
                                                                operation.getTransactionType()
                                                        )
            ) {
                System.out.printf("Годовой отчет не сходится с месячными! Внимательно проверьте отчет за %s\n",
                                  operation.getMonthName());
                return;
            }

        }

        System.out.println("Все отчеты сходятся!");
    }
}
