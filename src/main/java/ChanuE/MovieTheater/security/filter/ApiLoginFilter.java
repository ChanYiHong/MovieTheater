package ChanuE.MovieTheater.security.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 특정한 URL로 외부에서 로그인이 가능하도록 하고, 로그인이 성공하면 클라이언트가 Authorization 헤더의 값으로 이용할 데이터를 전송하게 함 **/
@Log4j2
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter {

    // 문자열로 패턴을 받는 생성자 반드시 필요
    public ApiLoginFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        log.info("----------------------API LOGIN FILTER------------------------");
        log.info("attemptAuthentication");

        String email = request.getParameter("email");
        String pw = "1111";

        if(email == null) {
            throw new BadCredentialsException("email cannot be null");
        }

        return null;
    }
}
