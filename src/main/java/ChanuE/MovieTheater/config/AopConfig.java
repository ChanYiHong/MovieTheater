package ChanuE.MovieTheater.config;

import ChanuE.MovieTheater.config.aop.LogTraceAspect;
import ChanuE.MovieTheater.log.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AopConfig {

    @Bean
    public LogTraceAspect logTraceAspect(LogTrace logTrace) {
        return new LogTraceAspect(logTrace);
    }

}
