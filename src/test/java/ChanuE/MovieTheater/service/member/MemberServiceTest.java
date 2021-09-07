package ChanuE.MovieTheater.service.member;

import ChanuE.MovieTheater.dto.member.MemberInfoDTO;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.security.dto.AuthMemberDTO;
import ChanuE.MovieTheater.security.service.MemberDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberDetailsService memberDetailsService;

    @Test
    void getMemberInfoTest() {

        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        AuthMemberDTO memberDTO = (AuthMemberDTO) memberDetailsService.loadUserByUsername("main@hcy.com");

        MemberInfoDTO memberInfo = memberService.getMemberInfo(memberDTO, pageRequestDTO);

        System.out.println(memberInfo);

    }
}