package ChanuE.MovieTheater.security.service;

import ChanuE.MovieTheater.domain.Member;
import ChanuE.MovieTheater.domain.MemberRole;
import ChanuE.MovieTheater.security.dto.AuthMemberDTO;
import ChanuE.MovieTheater.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/** 소셜 로그인에서 로그인 처리가 끝난 "결과" 를 가져오는 처리를 하는 환경
 * OAuth2UserService 인터페이스를 구현한 DefaultOAuth2UserService 상속.
 * UserDetailsService와 마찬가지로 OAuth2UserService도 인증을 담당하는 AuthenticationManager가 내부적으로 호출해서 사용자 정보를 가져옴**/
@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserDetailsService extends DefaultOAuth2UserService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info("여기 나와요?????????");
        log.info("====================");

        log.info("++++++++++++++++++++");
        log.info("++++++++++++++++++++");
        log.info("++++++++++++++++++++");
        log.info("++++++++++++++++++++");
        log.info("++++++++++++++++++++");

        System.out.println("여기 나와요?");
        System.out.println("여기 나와요?");
        System.out.println("여기 나와요?");
        System.out.println("여기 나와요?");
        System.out.println("여기 나와요?");

        log.info("userRequest: " + userRequest);

        return super.loadUser(userRequest);
    }

//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//
//        log.info("===============여기 안나오나요???==============");
//        log.info("-------------------------------");
//        log.info("userRequest : " + userRequest);
//
//        String clientName = userRequest.getClientRegistration().getClientName();
//
//        log.info("clientName : " + clientName);
//        log.info(userRequest.getAdditionalParameters());
//
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//
//        log.info("================================");
//        oAuth2User.getAttributes().forEach((k,v) -> {
//            log.info(k + " : " + v);
//        });
//
//        Map<String, Object> attributes = oAuth2User.getAttributes();
//        String email = null;
//
//        if(clientName.equals("Google")){
//            email = (String) attributes.get("email");
//        }
//
//        log.info("EMAIL: " + email);
//
//        Member member = saveSocialMember(email);
//
//        AuthMemberDTO authMemberDTO = new AuthMemberDTO(
//                member.getEmail(),
//                member.getPassword(),
//                true,
//                member.getRoleSet().stream().map(
//                        role -> new SimpleGrantedAuthority("ROLE_" + role.name())
//                ).collect(Collectors.toSet()),
//                oAuth2User.getAttributes()
//        );
//        authMemberDTO.setName(member.getName());
//
//        return authMemberDTO;
//    }
//
//    private Member saveSocialMember(String email) {
//
//        // 기존에 동일한 이메일로 가입한 회원이 있는 경우는 그냥 조회만.
//        Optional<Member> result = memberRepository.findByEmail(true, email);
//
//        if(result.isPresent()) {
//            return result.get();
//        }
//
//        // 최초 가입이면 회원 추가!, 일단 비번은 1111, 이름은 이메일 주소로...
//        Member member = Member.builder()
//                .email(email)
//                .password(passwordEncoder.encode("1111"))
//                .fromSocial(true)
//                .build();
//
//        member.addMemberRole(MemberRole.USER);
//        memberRepository.save(member);
//
//        return member;
//
//    }


}
