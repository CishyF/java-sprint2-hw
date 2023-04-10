import java.util.Scanner;

public class Main {

    private static final Scanner in = new Scanner(System.in);
    private static final CSVFileReader CSV_FILE_READER = CSVFileReader.INSTANCE;
    private static final CSVFileParser CSV_FILE_PARSER = CSVFileParser.INSTANCE;

    public static void main(String[] args) {

        boolean isMonthReportsScanned = false, isYearReportScanned = false;

        while (true) {
            printMenu();
            System.out.println("Введите номер команды, которую вы хотели бы использовать:");
            int command = in.nextInt();

            switch (command) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                default:
                    System.out.println("Вы ввели неверную команду, попробуйте снова");
            }

        }
    }

    private static void printMenu() {
        String menu = "1 - Считать все месячные отчёты;\n" +
                      "2 - Считать годовой отчёт;\n" +
                      "3 - Сверить отчёты;\n" +
                      "4 - Вывести информацию о всех месячных отчётах;\n" +
                      "5 - Вывести информацию о годовом отчёте.";
        System.out.println(menu);
    }
}

