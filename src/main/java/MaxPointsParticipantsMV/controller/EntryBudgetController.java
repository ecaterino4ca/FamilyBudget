package MaxPointsParticipantsMV.controller;

import MaxPointsParticipantsMV.exceptions.InvalidBudgetValueException;
import MaxPointsParticipantsMV.exceptions.InvalidTypeException;
import MaxPointsParticipantsMV.model.EntryBudget;
import MaxPointsParticipantsMV.service.EntryBudgetService;

import java.util.List;

public class EntryBudgetController {

    private EntryBudgetService entryBudgetService;

    public EntryBudgetController(EntryBudgetService entryBudgetService) {
        this.entryBudgetService = entryBudgetService;
    }

    public void addBudgetEntry(EntryBudget entryBudget) throws InvalidTypeException, InvalidBudgetValueException {
        entryBudgetService.addEntry(entryBudget);
    }

    public List<EntryBudget> getBudgetEntriesForMember(Integer id){
        return entryBudgetService.getBudgetEntriesForMemberWithId(id);
    }
}
