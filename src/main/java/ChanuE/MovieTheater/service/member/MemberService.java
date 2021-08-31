package ChanuE.MovieTheater.service.member;

import ChanuE.MovieTheater.domain.Member;
import ChanuE.MovieTheater.dto.member.MemberSaveDTO;

public interface MemberService {

    void joinMember(MemberSaveDTO memberSaveDTO);

}
