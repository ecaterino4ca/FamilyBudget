package MaxPointsParticipantsMV.controller;

import MaxPointsParticipantsMV.exceptions.DuplicateMemberIdException;
import MaxPointsParticipantsMV.exceptions.InvalidNameException;
import MaxPointsParticipantsMV.model.Member;
import MaxPointsParticipantsMV.model.MemberValidator;
import MaxPointsParticipantsMV.repository.MemberRepository;
import MaxPointsParticipantsMV.service.MemberService;
import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;

public class MemberControllerTest extends TestCase {

    private MemberService memberService;
    private MemberRepository memberRepository;
    private static final String TEST_FILE = "testMembers.txt";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    public void setUp() throws Exception {
        memberRepository = new MemberRepository(TEST_FILE);
        MemberValidator validator = new MemberValidator(memberRepository);
        memberService = new MemberService(memberRepository, validator);
        super.setUp();
    }

    public void testAddMemberSuccess() throws InvalidNameException, DuplicateMemberIdException {
        memberService.addMember(new Member("Kate", 3));
        assertTrue( memberRepository.hasMemberWithId(3));
    }

    @Test
    public void testAddMemberDuplicateId() {
        try {
            memberService.addMember(new Member("Alice", 1));
            fail();
        } catch (InvalidNameException | DuplicateMemberIdException e) {
            org.junit.Assert.assertThat(e.getMessage(), is("A member with this id was already added"));
        }
    }

    @Test
    public void testAddMemberInvalidIdMinExtreme() {
        try {
            memberService.addMember(new Member("Alice", -1));
            fail();
        } catch (InvalidNameException | DuplicateMemberIdException e) {
            org.junit.Assert.assertThat(e.getMessage(), is("Invalid ID"));
        }
    }

    @Test
    public void testAddMemberInvalidIdMaxExtreme() {
        try {
            memberService.addMember(new Member("Alice", Integer.MAX_VALUE+1));
            fail();
        } catch (InvalidNameException | DuplicateMemberIdException e) {
            org.junit.Assert.assertThat(e.getMessage(), is("Invalid ID"));
        }
    }

    @Test
    public void testAddMemberInvalidNameNotAlfa() {
        try {
            memberService.addMember(new Member("#gda%8", 7));
            fail();
        } catch (InvalidNameException | DuplicateMemberIdException e) {
            org.junit.Assert.assertThat(e.getMessage(), is("Name must contain only alphabetic characters"));
        }
    }
}