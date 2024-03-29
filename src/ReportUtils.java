import java.util.*;
import java.util.stream.Collectors;

public class ReportUtils {

    private ReportUtils() {}

    public static MonthlyReport parseTableToMonthlyReport(List<String> table, String path) {
        // Проверка корректности таблицы
        Objects.requireNonNull(table);
        table.forEach(Objects::requireNonNull);
        // Удаление заголовка отчета
        table.remove(0);
        // Получение имени месяца через путь к нему
        String monthName = convertNumberToMonth(path.split("\\.")[1].substring(4, 6));
        // Разбиваем каждую строку .csv файла по запятым
        List<String[]> parsedTable = table.stream().map(row -> row.split(",")).collect(Collectors.toList());

        MonthlyReport monthlyReport = new MonthlyReport(monthName);

        for (String[] splitRow : parsedTable) {

            String itemName;
            boolean isExpense;
            int itemQuantity, itemSumOfOne;

            try {
                itemName = splitRow[0];
                isExpense = Boolean.parseBoolean(splitRow[1]);
                itemQuantity = Integer.parseInt(splitRow[2]);
                itemSumOfOne = Integer.parseInt(splitRow[3]);

                monthlyReport.addNewOperation(itemName, isExpense, itemQuantity, itemSumOfOne);
            } catch (IndexOutOfBoundsException e) {
                throw new RuntimeException("Неверно составлена таблица " + path + "!");
            }
        }

        return monthlyReport;
    }

    public static YearlyReport parseTableToYearlyReport(List<String> table, String path) {
        // Проверка корректности таблицы
        Objects.requireNonNull(table);
        table.forEach(Objects::requireNonNull);
        // Удаление заголовка отчета
        table.remove(0);
        // Получение номера года
        String yearNumber = path.split("\\.")[1];
        // Разбиваем каждую строку .csv файла по запятым
        List<String[]> parsedTable = table.stream().map(row -> row.split(",")).collect(Collectors.toList());
        // Ассоциативный массив для кэширования объектов MonthOperationsRecord
        Map<String, MonthOperationsRecord> operationsRecordByMonth = new HashMap<>();

        YearlyReport yearlyReport = new YearlyReport(yearNumber);

        for (String[] splitRow : parsedTable) {

            String monthName;
            int amount;
            boolean isExpense;

            try {
                monthName = convertNumberToMonth(splitRow[0]);
                amount = Integer.parseInt(splitRow[1]);
                isExpense = Boolean.parseBoolean(splitRow[2]);

                if (operationsRecordByMonth.containsKey(monthName)) {

                    operationsRecordByMonth.get(monthName).addNewSum(isExpense, amount);
                } else {
                    var tempRecord = new MonthOperationsRecord(monthName, amount, isExpense);

                    yearlyReport.addNewMonthOperations(tempRecord);
                    operationsRecordByMonth.put(monthName, tempRecord);
                    System.out.println(operationsRecordByMonth);
                }
            } catch (IndexOutOfBoundsException e) {
                throw new RuntimeException("Неверно составлена таблица " + path + "!");
            }
        }

        return yearlyReport;
    }

    public static void compareReports(List<MonthlyReport> monthlyReports, YearlyReport yearlyReport) {

        List<MonthOperationsRecord> monthsRecords = yearlyReport.getMonthOperationsRecords();

        if (monthsRecords.size() != monthlyReports.size()) {
            System.out.println("При сверке данных отчетов обнаружено несоответсвие размеров!");
            return;
        }

        for (int i = 0; i < monthlyReports.size(); i++) {
            if (
                (monthlyReports.get(i).getTotalSumOfMonthOperation(true) !=
                                                monthsRecords.get(i).getExpenseSum()) ||
                (monthlyReports.get(i).getTotalSumOfMonthOperation(false) !=
                                                    monthsRecords.get(i).getEarningSum())
            ) {

                System.out.printf("При сверке данных отчетов обнаружена ошибка за %s!\n",
                                  monthlyReports.get(i).getMonthName()
                );
                return;
            }
        }

        System.out.println("Все отчеты сходятся!");
    }

    private static String convertNumberToMonth(String number) {
        Objects.requireNonNull(number);

        switch (number) {
            case "01":
                return "январь";
            case "02":
                return "февраль";
            case "03":
                return "март";
            case "04":
                return "апрель";
            case "05":
                return "май";
            case "06":
                return "июнь";
            case "07":
                return "июль";
            case "08":
                return "август";
            case "09":
                return "сентябрь";
            case "10":
                return "октябрь";
            case "11":
                return "ноябрь";
            case "12":
                return "декабрь";
            default:
                throw new RuntimeException("Некорректно указан месяц в файле отчета!");
        }
    }
}
