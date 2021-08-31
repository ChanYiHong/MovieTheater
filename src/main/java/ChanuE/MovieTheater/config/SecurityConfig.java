package ChanuE.MovieTheater.config;

import ChanuE.MovieTheater.security.filter.ApiCheckFilter;
import ChanuE.MovieTheater.security.filter.ApiLoginFilter;
import ChanuE.MovieTheater.security.handler.MemberLoginSuccessHandler;
import ChanuE.MovieTheater.security.service.MemberDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Log4j2
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // annotation으로 URL 접근 제한 지정.
/** 모든 시큐리티 관련 설정이 추가되는 부분 **/
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberDetailsService memberDetailsService;


    /** Password를 암호화 해주는 Encoder의 구현체인 BCryptPasswordEncoder를 Bean으로 등 **/
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.csrf().disable();
//        http.oauth2Login();

        //http.rememberMe().tokenValiditySeconds(60 * 60 * 24 * 7).userDetailsService(memberDetailsService);

        //http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
        //http.addFilterBefore(apiLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }



    @Bean
    public ApiLoginFilter apiLoginFilter() throws Exception {
        ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/api/login");
        apiLoginFilter.setAuthenticationManager(authenticationManager());
        return apiLoginFilter;
    }

    @Bean
    public MemberLoginSuccessHandler successHandler() {
        return new MemberLoginSuccessHandler(passwordEncoder());
    }

    @Bean
    public ApiCheckFilter apiCheckFilter() {
        return new ApiCheckFilter("/reviews/**/*");
    }
}
