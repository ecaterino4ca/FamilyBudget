package MaxPointsParticipantsMV.controller;

import MaxPointsParticipantsMV.exceptions.InvalidBudgetValueException;
import MaxPointsParticipantsMV.exceptions.InvalidTypeException;
import MaxPointsParticipantsMV.model.BudgetEntryValidator;
import MaxPointsParticipantsMV.model.EntryBudget;
import MaxPointsParticipantsMV.repository.EntryBudgetRepository;
import MaxPointsParticipantsMV.service.EntryBudgetService;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class EntryBudgetControllerTest extends TestCase {
    private EntryBudgetRepository entryBudgetRepository;
    private EntryBudgetService entryBudgetService;
    private EntryBudgetController entryBudgetController;
    private static final String TEST_FILE = "testBudget.txt";

    public void setUp() throws Exception {
        entryBudgetRepository = new EntryBudgetRepository(TEST_FILE);
        BudgetEntryValidator validator = new BudgetEntryValidator();
        entryBudgetService = new EntryBudgetService(entryBudgetRepository, validator);
        entryBudgetController = new EntryBudgetController(entryBudgetService);
        super.setUp();
    }

    @Test
    public void testAddBudgetEntrySuccess() throws InvalidTypeException, InvalidBudgetValueException, IOException {
        Integer size = entryBudgetRepository.size();
        entryBudgetController.addBudgetEntry(new EntryBudget("Income", 60, 2));
        size++;
        assertEquals(size, entryBudgetRepository.size());
    }

    @Test
    public void testAddBudgetEntryWithInvalidType() {
        try {
            entryBudgetController.addBudgetEntry(new EntryBudget("lala", 40, 1));
            fail();
        } catch (InvalidTypeException | IOException | InvalidBudgetValueException e) {
            org.junit.Assert.assertThat(e.getMessage(), is("A budget entry can be only cost or income"));
        }
    }

    @Test
    public void testAddBudgetEntryWithInvalidValue() {
        try {
            entryBudgetController.addBudgetEntry(new EntryBudget("income", -40, 1));
            fail();
        } catch (InvalidTypeException | IOException | InvalidBudgetValueException e) {
            org.junit.Assert.assertThat(e.getMessage(), is("A budget entry value can only be positive number"));
        }
    }

    @Test
    public void testGetBudgetEntriesForMember(){
        List<EntryBudget> budgetEntriesForMember = entryBudgetController.getBudgetEntriesForMember(1);
        assertEquals(1, budgetEntriesForMember.size());
    }

    @Test
    public void testInitializeWithErrorFile() throws IOException {
        entryBudgetRepository = new EntryBudgetRepository("testBudgetError.txt");
        BudgetEntryValidator validator = new BudgetEntryValidator();
        entryBudgetService = new EntryBudgetService(entryBudgetRepository, validator);
        entryBudgetController = new EntryBudgetController(entryBudgetService);
        try {
            entryBudgetController.addBudgetEntry(new EntryBudget("lala", 40, 1));
            fail();
        } catch (InvalidTypeException | IOException| InvalidBudgetValueException e) {
            assertTrue(e instanceof InvalidTypeException);
            e.printStackTrace();
        }
    }

    @Test
    public void testNonExistingFile() {
        try {
            entryBudgetRepository = new EntryBudgetRepository("blabla.txt");
            fail();
        } catch (IOException e) {
            assertTrue(e instanceof IOException);
            e.printStackTrace();
        }
    }
}