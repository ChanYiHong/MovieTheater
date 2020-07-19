package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Member;
import ChanuE.MovieTheater.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    // == 회원 가입 == //

    @Transactional
    public Long join(Member member){
        //validateMember(member.getName());
        memberRepository.save(member);
        return member.getId();
    }


    // 중복된 회원이 있으면 컨트롤러에서 다시 입력해 달라는 alert와 함께, 다시 입력하게 함..
    public boolean validateMember(String name){
        List<Member> names = memberRepository.findByName(name);
        if(!names.isEmpty()){
            //throw new IllegalStateException("중복되는 회원입니다..");
            return false;
        }
        return true;
    }

    // == 로그인 == //

    public Member login(String nickname, String password) throws NoResultException{

        // user를 찾으면 user return, user가 없으면 exception
        try {
            Member user = memberRepository.findByIdPassword(nickname, password);
            return user;
        }catch(NoResultException e){
            System.out.println(e);
            return null;
        }
    }
}
