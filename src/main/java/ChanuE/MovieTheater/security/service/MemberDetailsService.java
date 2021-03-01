package ChanuE.MovieTheater.security.service;

import ChanuE.MovieTheater.domain.Member;
import ChanuE.MovieTheater.security.dto.AuthMemberDTO;
import ChanuE.MovieTheater.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

/** UserDetailsService와 마찬가지로 OAuth2UserService도 인증을 담당하는 AuthenticationManager가 내부적으로 호출해서 사용자 정보를 가져옴 **/
@Log4j2
@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("MemberDetailsService loadUserByUserName : " + username);

        Optional<Member> result = memberRepository.findByEmail(false, username);
        if(!result.isPresent()){
            throw new UsernameNotFoundException("Check Email or Social");
        }

        Member member = result.get();

        log.info("---------------------------------");
        log.info("member : " + member);
        log.info("---------------------------------");

        AuthMemberDTO authMemberDTO = new AuthMemberDTO(
                member.getEmail(),
                member.getPassword(),
                member.isFromSocial(),
                member.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).collect(Collectors.toSet())
        );

        authMemberDTO.setName(member.getName());

        return authMemberDTO;
    }
}
