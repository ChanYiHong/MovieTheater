package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Address;
import ChanuE.MovieTheater.domain.Member;
import ChanuE.MovieTheater.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member 웅아 = new Member();
        웅아.setName("은아");
        웅아.setNickname("lecjgn");
        웅아.setPassword("12345");
        웅아.setAddress(new Address("대한민국","lecjgn@naver.com"));


        //when
        Long 웅아아이디 = memberService.join(웅아);

        //then
        assertEquals("웅아가 저장되었나요?", 웅아, memberRepository.findOne(웅아아이디));
    }
}