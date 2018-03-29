package MaxPointsParticipantsMV.ui;

import MaxPointsParticipantsMV.controller.EntryBudgetController;
import MaxPointsParticipantsMV.controller.MemberController;
import MaxPointsParticipantsMV.exceptions.DuplicateMemberIdException;
import MaxPointsParticipantsMV.exceptions.InvalidBudgetValueException;
import MaxPointsParticipantsMV.exceptions.InvalidNameException;
import MaxPointsParticipantsMV.exceptions.InvalidTypeException;
import MaxPointsParticipantsMV.model.EntryBudget;
import MaxPointsParticipantsMV.model.Member;

import java.util.List;
import java.util.Scanner;

public class Console {
	private EntryBudgetController entryBudgetController;
	private MemberController memberController;
	private Scanner scanner;

	public Console(EntryBudgetController entryBudgetController, MemberController memberController) {
		this.entryBudgetController = entryBudgetController;
		this.memberController = memberController;
		this.scanner = new Scanner(System.in);
	}

	private void printMenu() {
		String menu = "Budget Admin Menu: \n";
		menu +="\t 1 - to add a new member; \n";
		menu +="\t 2 - to add a new budget entry (income/cost); \n";
		menu +="\t 3 - to list all the entries for a member; \n";
		menu +="\t 0 - exit \n";
		
		System.out.println(menu);
	}
	
	
	public void run() {
		printMenu();
		int command = scanner.nextInt();
		scanner.nextLine();
		
		while(command != 0) {
			if(command == 1) {
				System.out.println("Enter name:");
				String name = scanner.nextLine();
				System.out.println("Enter id:");
				String id = scanner.nextLine();
				Member member = new Member(name, Integer.parseInt(id));
                try {
                    memberController.addMember(member);
                } catch (DuplicateMemberIdException | InvalidNameException e) {
                    System.out.println(e.getMessage());
                }
            }
			if(command == 2) {
				System.out.println("Enter type:");
				String type = scanner.nextLine();
				
				System.out.println("Enter the value:");
				String valueS = scanner.nextLine();
				Integer valueInt = Integer.parseInt(valueS);
				
				System.out.println("Enter the id of the member:");
				String idS = scanner.nextLine();
				Integer idInt = Integer.parseInt(idS);
				
				EntryBudget entryBudget = new EntryBudget(type, valueInt, idInt);
                try {
                    entryBudgetController.addBudgetEntry(entryBudget);
                } catch (InvalidTypeException | InvalidBudgetValueException e) {
                    System.out.println(e.getMessage());
                }
            }
			if(command == 3) {
				System.out.println("Enter the id of the member:");
                String idS = scanner.nextLine();
                Integer idInt = Integer.parseInt(idS);

				List<EntryBudget> budgetEntries = entryBudgetController.getBudgetEntriesForMember(idInt);
				budgetEntries.forEach(entryBudget -> System.out.println(entryBudget.toString()));
				if(budgetEntries.isEmpty()){
                    System.out.println("No entries for this member id");
                }
			}

			printMenu();
			command = scanner.nextInt();
			scanner.nextLine();
		}
	}
}

