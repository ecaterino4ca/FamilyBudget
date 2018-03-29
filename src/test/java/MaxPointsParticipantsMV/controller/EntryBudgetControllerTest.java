package MaxPointsParticipantsMV.controller;

import MaxPointsParticipantsMV.exceptions.InvalidBudgetValueException;
import MaxPointsParticipantsMV.exceptions.InvalidTypeException;
import MaxPointsParticipantsMV.model.BudgetEntryValidator;
import MaxPointsParticipantsMV.model.EntryBudget;
import MaxPointsParticipantsMV.repository.EntryBudgetRepository;
import MaxPointsParticipantsMV.service.EntryBudgetService;
import junit.framework.TestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class EntryBudgetControllerTest extends TestCase {
    private EntryBudgetRepository entryBudgetRepository;
    private EntryBudgetService entryBudgetService;
    private static final String TEST_FILE = "testBudget.txt";

    public void setUp() throws Exception {
        entryBudgetRepository = new EntryBudgetRepository(TEST_FILE);
        BudgetEntryValidator validator = new BudgetEntryValidator();
        entryBudgetService = new EntryBudgetService(entryBudgetRepository, validator);
        super.setUp();
    }

    @Test
    public void testAddBudgetEntrySuccess() throws InvalidTypeException, InvalidBudgetValueException {
        Integer size = entryBudgetRepository.size();
        entryBudgetService.addEntry(new EntryBudget("Income", 60, 2));
        size++;
        assertEquals(size, entryBudgetRepository.size());
    }

    @Test
    public void testAddBudgetEntryWithInvalidType() {
        try {
            entryBudgetService.addEntry(new EntryBudget("lala", 40, 1));
            fail();
        } catch (InvalidTypeException | InvalidBudgetValueException e) {
            org.junit.Assert.assertThat(e.getMessage(), is("A budget entry can be only cost or income"));
        }
    }

}