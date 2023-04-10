class MonthOperation {

    private final String name;
    private final int amountOfOperation;
    private final TransactionType transactionType;

    public MonthOperation(String name, int amount, TransactionType type) {
        this.name = name;
        this.amountOfOperation = amount;
        this.transactionType = type;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amountOfOperation;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }
}
