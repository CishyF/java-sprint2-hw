import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner in = new Scanner(System.in);

    private static final CSVFileReader CSV_FILE_READER = CSVFileReader.INSTANCE;
    private static final CSVFileParser CSV_FILE_PARSER = CSVFileParser.INSTANCE;

    private static final String MONTHLY_REPORT_PATH_TEMPLATE = "resources/m.2021%02d.csv";
    private static final String ANNUAL_REPORT_PATH = "resources/y.2021.csv";
    private static final String SEPARATOR = "-".repeat(30);


    public static void main(String[] args) {

        List<MonthlyReport> monthlyReports = null;
        AnnualReport annualReport = null;

        while (true) {
            printMenu();
            System.out.println(SEPARATOR);
            System.out.println("Введите номер команды, которую вы хотели бы использовать:");
            int command = in.nextInt();
            System.out.println(SEPARATOR);
            switch (command) {
                case 0:
                    return;
                case 1:
                    monthlyReports = new ArrayList<>();

                    for (int i = 1; i < 4; i++) {
                        String path = String.format(MONTHLY_REPORT_PATH_TEMPLATE, i);

                        MonthlyReport monthlyReport = CSV_FILE_PARSER.parseTableToMonthlyReport(
                                CSV_FILE_READER.readCSVFileByRowsAndColumns(path),
                                path
                        );

                        monthlyReports.add(monthlyReport);
                    }

                    System.out.println("Файлы месячных отчетов успешно считаны.");
                    break;
                case 2:
                    annualReport = CSV_FILE_PARSER.parseTableToAnnualReport(
                            CSV_FILE_READER.readCSVFileByRowsAndColumns(ANNUAL_REPORT_PATH), ANNUAL_REPORT_PATH
                    );

                    System.out.println("Файл годового отчета успешно считан.");
                    break;
                case 3:
                    if (monthlyReports == null || annualReport == null) {
                        System.out.println("Вам необходимо считать годовой и месячные отчеты перед сравнением!");
                        break;
                    }
                    ReportsComparator.compareReports(monthlyReports, annualReport);
                    break;
                case 4:
                    if (monthlyReports == null) {
                        System.out.println("Вам необходимо считать месячные отчеты перед получением информации о них!");
                        break;
                    }

                    System.out.println(SEPARATOR);
                    System.out.println(" Информация о месячных отчетах");
                    System.out.println(SEPARATOR);

                    monthlyReports.forEach(MonthlyReport::printInfo);
                    break;
                case 5:
                    if (annualReport == null) {
                        System.out.println("Вам необходимо считать годовой отчет перед получением информации о нем!");
                        break;
                    }

                    System.out.println(SEPARATOR);
                    System.out.println(" Информация о годовом отчете");
                    System.out.println(SEPARATOR);
                    annualReport.printInfo();
                    break;
                default:
                    System.out.println("Вы ввели неверную команду, попробуйте снова");
            }

        }
    }

    private static void printMenu() {
        System.out.println(SEPARATOR);
        String menu = "1 - Считать все месячные отчёты;\n" +
                      "2 - Считать годовой отчёт;\n" +
                      "3 - Сверить отчёты;\n" +
                      "4 - Вывести информацию о всех месячных отчётах;\n" +
                      "5 - Вывести информацию о годовом отчёте;\n" +
                      "0 - Выйти из приложения.";
        System.out.println(menu);
    }
}

