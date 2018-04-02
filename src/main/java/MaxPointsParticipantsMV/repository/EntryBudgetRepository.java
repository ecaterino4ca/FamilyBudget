package MaxPointsParticipantsMV.repository;

import MaxPointsParticipantsMV.exceptions.InvalidBudgetValueException;
import MaxPointsParticipantsMV.exceptions.InvalidTypeException;
import MaxPointsParticipantsMV.model.BudgetEntryValidator;
import MaxPointsParticipantsMV.model.EntryBudget;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EntryBudgetRepository {

    private List<EntryBudget> butgetEntries = new ArrayList<>();
    private BudgetEntryValidator entryValidator;
    private String FILE_NAME = "budgetF.txt";

    public EntryBudgetRepository(String FILE_NAME) throws IOException {
        entryValidator = new BudgetEntryValidator();
        this.FILE_NAME = FILE_NAME;
        initializeRepository();
    }

    public void addEntry(EntryBudget entryBudget) throws IOException {
        butgetEntries.add(entryBudget);
        addToFile(entryBudget);
    }

    public List<EntryBudget> getBudgetEntriesForMemberWithId(Integer id){
        return butgetEntries.stream()
                .filter(entryBudget -> entryBudget.getIdMember().equals(id))
                .collect(Collectors.toList());
    }

    private void initializeRepository() throws IOException {
        try{
            FileReader fileReader = new FileReader(FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {
                String line[] = currentLine.split(";");
                EntryBudget entryBudget = new EntryBudget(line[0], Integer.valueOf(line[1]), Integer.valueOf(line[2]));
                entryValidator.validate(entryBudget);
                this.butgetEntries.add(entryBudget);
            }
        } catch (InvalidBudgetValueException | InvalidTypeException e) {
            e.printStackTrace();
        }
    }

    private void addToFile(EntryBudget entryBudget) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_NAME,true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.newLine();
        bufferedWriter.write( entryBudget.getTypeEntry() + ";" + entryBudget.getValue().toString() + ";" +
                entryBudget.getIdMember().toString());
        bufferedWriter.close();

    }

    public Integer size(){
        return butgetEntries.size();
    }
}
