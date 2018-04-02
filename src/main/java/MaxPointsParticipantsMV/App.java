package MaxPointsParticipantsMV;

import MaxPointsParticipantsMV.controller.EntryBudgetController;
import MaxPointsParticipantsMV.controller.MemberController;
import MaxPointsParticipantsMV.model.BudgetEntryValidator;
import MaxPointsParticipantsMV.model.MemberValidator;
import MaxPointsParticipantsMV.repository.EntryBudgetRepository;
import MaxPointsParticipantsMV.repository.MemberRepository;
import MaxPointsParticipantsMV.service.EntryBudgetService;
import MaxPointsParticipantsMV.service.MemberService;
import MaxPointsParticipantsMV.ui.Console;

import java.io.IOException;

public class App {
	public static void main(String[] args) {
		
		MemberRepository memberRepository = new MemberRepository();
		MemberValidator memberValidator = new MemberValidator(memberRepository);
		MemberService memberService = new MemberService(memberRepository, memberValidator);

		EntryBudgetRepository entryBudgetRepository = null;
		try {
			entryBudgetRepository = new EntryBudgetRepository("budgetF.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		EntryBudgetService entryBudgetService = new EntryBudgetService(entryBudgetRepository, new BudgetEntryValidator());

		MemberController memberController = new MemberController(memberService);
		EntryBudgetController entryBudgetController = new EntryBudgetController(entryBudgetService);

		Console console = new Console(entryBudgetController, memberController);
		console.run();
		
	}
}
