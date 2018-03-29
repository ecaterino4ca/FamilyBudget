package MaxPointsParticipantsMV.controller;

import MaxPointsParticipantsMV.exceptions.DuplicateMemberIdException;
import MaxPointsParticipantsMV.exceptions.InvalidNameException;
import MaxPointsParticipantsMV.model.Member;
import MaxPointsParticipantsMV.service.MemberService;

public class MemberController {
	
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    public void addMember(Member member) throws DuplicateMemberIdException, InvalidNameException {
        memberService.addMember(member);
    }

    public void updateFiles() {
        memberService.updateFiles();
    }
} 