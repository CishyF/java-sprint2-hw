public class OperationRecord implements Record {

    private final String name;
    private final int cost;
    private final int quantity;
    private final boolean isExpense;
    private int expenseSum;
    private int earningSum;

    public OperationRecord(String name, boolean isExpense, int quantity, int cost) {
        this.name = name;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.cost = cost;
        if (isExpense)
            expenseSum = quantity * cost;
        else
            earningSum = quantity * cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getQuantity() {
        return quantity;
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
        return "OperationRecord{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", quantity=" + quantity +
                ", isExpense=" + isExpense +
                '}';
    }
}
