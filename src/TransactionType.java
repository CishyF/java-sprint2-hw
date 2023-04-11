public enum TransactionType {
    EXPENSE, PROFIT;

    boolean isExpense() {
        return this == EXPENSE;
    }
}
