package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Member;
import ChanuE.MovieTheater.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        validateMember(member.getName());
        memberRepository.save(member);
        return member.getId();
    }


    private void validateMember(String name){
        List<Member> names = memberRepository.findByName(name);
        if(!names.isEmpty()){
            throw new IllegalStateException("중복되는 회원입니다..");
        }
    }

}
