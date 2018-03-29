package MaxPointsParticipantsMV.repository;

import MaxPointsParticipantsMV.exceptions.DuplicateMemberIdException;
import MaxPointsParticipantsMV.exceptions.InvalidNameException;
import MaxPointsParticipantsMV.model.Member;
import MaxPointsParticipantsMV.model.MemberValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
	private List<Member> members = new ArrayList<>();
	private MemberValidator memberValidator;
	private String FILE_NAME = "membersF.txt";

	public MemberRepository(String FILE_NAME) {
		memberValidator = new MemberValidator(this);
		this.FILE_NAME = FILE_NAME;
		initializeRepository();
	}

	public MemberRepository() {
		memberValidator = new MemberValidator(this);
		initializeRepository();
	}

	 public void addMember(Member member){
		 members.add(member);
	 }

	 public void addToFileAllMembers(){
		members.forEach(this::addToFile);
	 }

	 private void initializeRepository(){
		 try{
			 FileReader fileReader = new FileReader(FILE_NAME);
			 BufferedReader bufferedReader = new BufferedReader(fileReader);
			 String currentLine;

			 while ((currentLine = bufferedReader.readLine()) != null) {
				 String line[] = currentLine.split(";");
				 Member member = new Member(line[0], Integer.valueOf(line[1]));
				 memberValidator.validate(member);
				 this.members.add(member);
			 }
		 } catch (InvalidNameException | IOException | DuplicateMemberIdException e) {
			 e.printStackTrace();
		 }
	 }

	 public boolean hasMemberWithId(Integer givenId){
	    return members.stream()
                .map(Member::getId)
                .anyMatch(id -> id.equals(givenId));
     }

    private void addToFile(Member member){
        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
            bufferedWriter.write(member.getName() + ";" + member.getId().toString());
            bufferedWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
