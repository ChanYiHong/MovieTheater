package ChanuE.MovieTheater.security.filter;

import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/** 가장 일반적이고 매번 동작하는 기본적인 API 필터.
 * API를 사용할 때 자신의 고유한 KEY를 Client가 같이 전송하고, 이를 이용해서 해당 요청이 정상적인 사용자임을 알아내는 방식을 사용 **/
@Log4j2
public class ApiCheckFilter extends OncePerRequestFilter {

    private AntPathMatcher antPathMatcher;
    private String pattern;

    public ApiCheckFilter(String pattern) {
        this.antPathMatcher = new AntPathMatcher();
        this.pattern = pattern;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("REQUEST URI : " + request.getRequestURI());
        log.info(antPathMatcher.match(pattern, request.getRequestURI()));

        if(antPathMatcher.match(pattern, request.getRequestURI())) {

            log.info("API CHECK FILTER ........................");
            log.info("API CHECK FILTER ........................");
            log.info("API CHECK FILTER ........................");

            boolean checkHeader = checkAuthHeader(request);

            if(checkHeader) {
                filterChain.doFilter(request, response);
                return;
            }
            else{
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                // json 리턴 및 한글깨짐 수정.
                response.setContentType("application/json;charset=utf-8");
                JSONObject json = new JSONObject();
                String message = "FAIL CHECK API TOKEN";
                json.put("code", "403");
                json.put("message", message);

                PrintWriter out = response.getWriter();
                out.print(json);
                return;
            }
        }

        filterChain.doFilter(request, response);

    }

    /** 특정한 API를 호출하는 클라이언트에서는 다른 서버나 Application으로 실행되기 때문에 쿠키나 세션을 활용할 수 없다.
     * 이러한 제약 때문에 API를 호출하는 경우에는 Request를 전송할 때 Http 헤더 메세지에 특별한 값을 지정해서 전송한다.**/
    private boolean checkAuthHeader(HttpServletRequest request) {

        boolean checkResult = false;
        String authHeader = request.getHeader("Authorization");

        if(StringUtils.hasText(authHeader)) {
            log.info("Authorization exist: " + authHeader);
            if(authHeader.equals("12345678")){
                checkResult = true;
            }
        }
        return checkResult;
    }
}
