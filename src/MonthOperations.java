public class MonthOperations {

    private final String name;
    private final int amountOfOperation;
    private final TransactionType transactionType;

    public MonthOperations(String name, int amount, TransactionType transaction) {
        this.name = name;
        this.amountOfOperation = amount;
        this.transactionType = transaction;
    }

    public String getMonthName() {
        return name;
    }

    public int getAmount() {
        return amountOfOperation;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    @Override
    public String toString() {
        return "MonthOperations{" +
                "name='" + name + '\'' +
                ", amountOfOperation=" + amountOfOperation +
                ", transactionType=" + transactionType +
                '}';
    }
}
