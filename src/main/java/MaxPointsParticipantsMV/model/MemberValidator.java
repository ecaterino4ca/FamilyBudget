package MaxPointsParticipantsMV.model;

import MaxPointsParticipantsMV.exceptions.DuplicateMemberIdException;
import MaxPointsParticipantsMV.exceptions.InvalidNameException;
import MaxPointsParticipantsMV.repository.MemberRepository;

public class MemberValidator {

    private MemberRepository memberRepository;

    public MemberValidator(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void validate(Member member) throws DuplicateMemberIdException, InvalidNameException {
        if(member.getId() < 0 || member.getId() > Integer.MAX_VALUE) {
            throw new DuplicateMemberIdException("Invalid ID");
        }
        if(memberRepository.hasMemberWithId(member.getId())){
            throw new DuplicateMemberIdException("A member with this id was already added");
        }
        if(!isAlpha(member.getName())){
            throw new InvalidNameException("Name must contain only alphabetic characters");
        }
    }

    private boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }
}
