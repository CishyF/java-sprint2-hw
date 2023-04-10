public enum TransactionType {
    EXPENSE, PROFIT;

    public boolean isExpense() {
        return this == EXPENSE;
    }
}
