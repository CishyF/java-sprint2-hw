import java.util.*;
import java.util.ArrayList;

public class CSVFileParser {

    public static final CSVFileParser INSTANCE = new CSVFileParser();

    private CSVFileParser() {}

    public MonthlyReport parseTableToMonthlyReport(List<String[]> table, String path) {
        // Проверка корректности таблицы
        Objects.requireNonNull(table);
        table.forEach(Objects::requireNonNull);
        // Удаление заголовка отчета
        table.remove(0);

        String monthName = convertNumberToMonth(path.split("\\.")[1].substring(4, 6));

        Map<TransactionType, List<String>> itemsNamesByTransactionType = new HashMap<>();
        Map<String, Integer> itemQuantityByName = new HashMap<>();
        Map<String, Integer> itemCostByName = new HashMap<>();

        for (String[] splitRow : table) {

            String itemName;
            TransactionType transaction;
            int itemQuantity, itemCost;

            try {
                itemName = splitRow[0];
                transaction = splitRow[1].equals("TRUE") ? TransactionType.EXPENSE
                                                         : TransactionType.PROFIT;
                itemQuantity = Integer.parseInt(splitRow[2]);
                itemCost = Integer.parseInt(splitRow[3]);
            } catch (IndexOutOfBoundsException e) {
                throw new RuntimeException("Неверно составлена таблица " + path + "!");
            }


            if (!itemsNamesByTransactionType.containsKey(transaction))
                itemsNamesByTransactionType.put(transaction, new ArrayList<>());

            itemsNamesByTransactionType.get(transaction).add(itemName);

            itemQuantityByName.put(itemName, itemQuantity);
            itemCostByName.put(itemName, itemCost);

        }

        return new MonthlyReport(
                monthName,
                itemsNamesByTransactionType,
                itemQuantityByName,
                itemCostByName
        );

    }

    public AnnualReport parseTableToAnnualReport(List<String[]> table, String path) {
        // Проверка корректности таблицы
        Objects.requireNonNull(table);
        table.forEach(Objects::requireNonNull);
        // Удаление заголовка отчета
        table.remove(0);

        AnnualReport annualReport = new AnnualReport(path);

        for (String[] splitRow : table) {

            String monthName;
            TransactionType transaction;
            int amount;

            try {
                monthName = convertNumberToMonth(splitRow[0]);
                transaction = splitRow[2].equals("true") ? TransactionType.EXPENSE
                                                         : TransactionType.PROFIT;
                amount = Integer.parseInt(splitRow[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new RuntimeException("Неверно составлена таблица " + path + "!");
            }

            annualReport.addNewMonthOperation(monthName, amount, transaction);

        }

        return annualReport;
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
