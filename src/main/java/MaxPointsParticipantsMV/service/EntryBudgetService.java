package MaxPointsParticipantsMV.service;

import MaxPointsParticipantsMV.exceptions.InvalidBudgetValueException;
import MaxPointsParticipantsMV.exceptions.InvalidTypeException;
import MaxPointsParticipantsMV.model.BudgetEntryValidator;
import MaxPointsParticipantsMV.model.EntryBudget;
import MaxPointsParticipantsMV.repository.EntryBudgetRepository;

import java.util.List;

public class EntryBudgetService {
    private EntryBudgetRepository entryBudgetRepository;
    private BudgetEntryValidator entryValidator;

    public EntryBudgetService(EntryBudgetRepository entryBudgetRepository, BudgetEntryValidator entryValidator) {
        this.entryBudgetRepository = entryBudgetRepository;
        this.entryValidator = entryValidator;
    }

    public void addEntry(EntryBudget entryBudget) throws InvalidBudgetValueException, InvalidTypeException {
        entryValidator.validate(entryBudget);
        entryBudgetRepository.addEntry(entryBudget);
    }

    public List<EntryBudget> getBudgetEntriesForMemberWithId(Integer id){
        return entryBudgetRepository.getBudgetEntriesForMemberWithId(id);
    }
}
