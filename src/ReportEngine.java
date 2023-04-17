import java.util.ArrayList;
import java.util.List;

public class ReportEngine {

    private static final String MONTHLY_REPORT_PATH_TEMPLATE = "resources/m.2021%02d.csv";
    private static final String YEARLY_REPORT_PATH = "resources/y.2021.csv";

    private List<MonthlyReport> monthlyReports;
    private YearlyReport yearlyReport;

    public void readMonthlyReports() {
        monthlyReports = new ArrayList<>();

        for (int i = 1; i < 4; i++) {
            String path = String.format(MONTHLY_REPORT_PATH_TEMPLATE, i);

            MonthlyReport monthlyReport = ReportUtils.parseTableToMonthlyReport(
                    CSVFileReader.readCSVFileByRows(path),
                    path
            );

            monthlyReports.add(monthlyReport);
        }

        System.out.println("Файлы месячных отчетов успешно считаны.");
    }

    public void readYearlyReport() {
        yearlyReport = ReportUtils.parseTableToYearlyReport(
                CSVFileReader.readCSVFileByRows(YEARLY_REPORT_PATH), YEARLY_REPORT_PATH
        );

        System.out.println("Файл годового отчета успешно считан.");
    }

    public void compareReports() {
        if (monthlyReports == null || yearlyReport == null) {
            System.out.println("Вам необходимо считать годовой и месячные отчеты перед сравнением!");
            return;
        }
        ReportUtils.compareReports(monthlyReports, yearlyReport);
    }

    public void showMonthlyReportsInfo(String sep) {
        if (monthlyReports == null) {
            System.out.println("Вам необходимо считать месячные отчеты перед получением информации о них!");
            return;
        }

        System.out.println(" Информация о месячных отчетах");
        System.out.println(sep);

        monthlyReports.forEach(MonthlyReport::printInfo);
    }

    public void showYearlyReportInfo(String sep) {
        if (yearlyReport == null) {
            System.out.println("Вам необходимо считать годовой отчет перед получением информации о нем!");
            return;
        }

        System.out.println(" Информация о годовом отчете");
        System.out.println(sep);
        yearlyReport.printInfo();
    }
}
