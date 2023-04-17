public class OperationRecord {

    private final String name;
    private final int sumOfOne;
    private final int quantity;
    private final boolean isExpense;

    public OperationRecord(String name, boolean isExpense, int quantity, int sumOfOne) {
        this.name = name;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }

    public String getName() {
        return name;
    }

    public int getSumOfOne() {
        return sumOfOne;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSum() {
        return sumOfOne * quantity;
    }

    public boolean isExpense() {
        return isExpense;
    }

    @Override
    public String toString() {
        return "OperationRecord{" +
                "name='" + name + '\'' +
                ", sumOfOne=" + sumOfOne +
                ", quantity=" + quantity +
                ", isExpense=" + isExpense +
                '}';
    }
}
