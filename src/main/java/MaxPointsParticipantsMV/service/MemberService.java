package MaxPointsParticipantsMV.service;

import MaxPointsParticipantsMV.exceptions.DuplicateMemberIdException;
import MaxPointsParticipantsMV.exceptions.InvalidNameException;
import MaxPointsParticipantsMV.model.Member;
import MaxPointsParticipantsMV.model.MemberValidator;
import MaxPointsParticipantsMV.repository.MemberRepository;

public class MemberService {
    private MemberRepository memberRepository;
    private MemberValidator memberValidator;

    public MemberService(MemberRepository memberRepository, MemberValidator memberValidator) {
        this.memberRepository = memberRepository;
        this.memberValidator = memberValidator;
    }
    public void addMember(Member member) throws InvalidNameException, DuplicateMemberIdException {
        memberValidator.validate(member);
        memberRepository.addMember(member);
    }

    public void updateFiles() {
        memberRepository.addToFileAllMembers();
    }
}
