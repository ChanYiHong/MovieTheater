package ChanuE.MovieTheater.security;

import ChanuE.MovieTheater.domain.Member;
import ChanuE.MovieTheater.domain.MemberRole;
import ChanuE.MovieTheater.repository.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies() throws Exception {

        IntStream.rangeClosed(1, 20).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@hcy.com")
                    .name("user" + i)
                    .fromSocial(false)
                    .password(passwordEncoder.encode("1111"))
                    .build();

            if(i == 1) member.addMemberRole(MemberRole.ADMIN);

            member.addMemberRole(MemberRole.USER);

            memberRepository.save(member);
        });

    }

    @Test
    public void getMember() throws Exception {

        Optional<Member> result = memberRepository.findByEmail(false, "user1@hcy.com");

        System.out.println(result.get());

    }
}
