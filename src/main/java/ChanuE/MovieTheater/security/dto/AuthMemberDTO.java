package ChanuE.MovieTheater.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

/** UserDetailsService 구현체 메소드 loadUserByUsername() 의 리턴 타입은 UserDetails인데 이를 구현한 클래스를 상속해
 * 회원정보를 리턴하게끔 만든다. 기존 DTO 역할과 비슷. 사용자 정보를 담은 객체. **/
@Log4j2
@Getter
@Setter
@ToString
public class AuthMemberDTO extends User implements OAuth2User {

    private String email;
    private String password;
    private String name;
    private boolean fromSocial;
    private Map<String, Object> attr;

    public AuthMemberDTO(String username,
                         String password,
                         boolean fromSocial,
                         Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = username;
        this.password = password;
        this.fromSocial = fromSocial;
    }

    public AuthMemberDTO(String username,
                         String password,
                         boolean fromSocial,
                         Collection<? extends GrantedAuthority> authorities,
                         Map<String, Object> attr) {
        this(username, password, fromSocial, authorities);
        this.attr = attr;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attr;
    }
}
