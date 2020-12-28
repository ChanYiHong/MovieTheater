package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Member;
import ChanuE.MovieTheater.dto.member.MemberResponseDto;
import ChanuE.MovieTheater.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<MemberResponseDto> findAll(){
        List<Member> result = memberRepository.findAll();
        return result.stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }

}
