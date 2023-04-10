import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AnnualReport {

    private final List<MonthOperation> monthOperations;

    public AnnualReport() {
        monthOperations = new ArrayList<>();
    }

    public void addNewMonthOperation(String monthName, int amount, TransactionType type) {
        Objects.requireNonNull(monthName);
        MonthOperation operation = new MonthOperation(monthName, amount, type);
        monthOperations.add(operation);
    }

    public List<MonthOperation> getMonthOperations() {
        List<MonthOperation> MonthOperationsCopy = new ArrayList<>();
        Collections.copy(monthOperations, MonthOperationsCopy);
        return MonthOperationsCopy;
    }

}
