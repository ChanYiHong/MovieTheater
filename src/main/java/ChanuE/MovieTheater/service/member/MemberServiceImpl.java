package ChanuE.MovieTheater.service.member;

import ChanuE.MovieTheater.domain.Member;
import ChanuE.MovieTheater.domain.MemberRole;
import ChanuE.MovieTheater.dto.member.MemberSaveDTO;
import ChanuE.MovieTheater.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void joinMember(MemberSaveDTO memberSaveDTO) {

        Set<MemberRole> roleSet = new HashSet<>();
        roleSet.add(MemberRole.USER);

        Member member = Member.builder()
                .email(memberSaveDTO.getEmail())
                .password(passwordEncoder.encode(memberSaveDTO.getPassword()))
                .name(memberSaveDTO.getName())
                .fromSocial(false)
                .roleSet(roleSet)
                .build();

        memberRepository.save(member);

    }
}
