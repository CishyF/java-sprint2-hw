public class MonthOperationsRecord {

    private final String name;
    private final int amount;
    private int expenseSum;
    private int earningSum;

    public MonthOperationsRecord(String name, int amount, boolean isExpense) {
        this.name = name;
        this.amount = amount;
        if (isExpense)
            expenseSum = amount;
        else
            earningSum = amount;
    }

    public String getMonthName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getExpenseSum() {
        return expenseSum;
    }

    public int getEarningSum() {
        return earningSum;
    }

    public void addNewSum(boolean isExpense, int amount) {
        if (isExpense)
            expenseSum += amount;
        else
            earningSum += amount;
    }

    @Override
    public String toString() {
        return "MonthOperationsRecord{" +
                "name='" + name + '\'' +
                ", expenseSum=" + expenseSum +
                ", earningSum=" + earningSum +
                '}';
    }
}
