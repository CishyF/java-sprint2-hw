import java.util.Scanner;

public class Main {

    private static final Scanner in = new Scanner(System.in);
    private static final String SEPARATOR = "-".repeat(30);

    public static void main(String[] args) {

        ReportEngine reportEngine = new ReportEngine();

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
                    reportEngine.readMonthlyReports();
                    break;
                case 2:
                    reportEngine.readYearlyReport();
                    break;
                case 3:
                    reportEngine.compareReports();
                    break;
                case 4:
                    reportEngine.showMonthlyReportsInfo(SEPARATOR);
                    break;
                case 5:
                    reportEngine.showYearlyReportInfo(SEPARATOR);
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

