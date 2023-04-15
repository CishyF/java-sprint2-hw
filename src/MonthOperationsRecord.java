public class MonthOperationsRecord implements Record {

    private final String name;
    private final int amount;
    private final boolean isExpense;
    private int expenseSum;
    private int earningSum;

    public MonthOperationsRecord(String name, int amount, boolean isExpense) {
        this.name = name;
        this.amount = amount;
        this.isExpense = isExpense;
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

    public boolean isExpense() {
        return isExpense;
    }

    public int getExpenseSum() {
        return expenseSum;
    }

    public int getEarningSum() {
        return earningSum;
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
