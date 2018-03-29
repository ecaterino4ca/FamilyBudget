package MaxPointsParticipantsMV.model;

import MaxPointsParticipantsMV.exceptions.InvalidBudgetValueException;
import MaxPointsParticipantsMV.exceptions.InvalidTypeException;

public class BudgetEntryValidator {

    public BudgetEntryValidator() {
    }

    public void validate(EntryBudget entryBudget) throws InvalidTypeException, InvalidBudgetValueException {
        if(!(entryBudget.getTypeEntry().equalsIgnoreCase("cost") ||
                entryBudget.getTypeEntry().equalsIgnoreCase("income"))){
            throw new InvalidTypeException("A budget entry can be only cost or income");
        }
        if(entryBudget.getValue() < 0){
            throw new InvalidBudgetValueException("A budget entry value can only be positive number");
        }
        // shall also validate that there is such a member with the given id
    }

}
