package cn.saintshaga.auth.security;

import cn.saintshaga.auth.security.handler.ChainAuthenticationFailureHandler;
import cn.saintshaga.auth.security.handler.ChainAuthenticationSuccessHandler;
import cn.saintshaga.auth.security.huenative.CollabCallHueNativeAuthenticationFailureHandler;
import cn.saintshaga.auth.security.huenative.CollabCallHueNativeAuthenticationFilter;
import cn.saintshaga.auth.security.huenative.CollabCallHueNativeAuthenticationProvider;
import cn.saintshaga.auth.security.huenative.CollabCallHueNativeAuthenticationSuccessHandler;
import com.worksap.company.hue.restclient.config.RestClientForNativeConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * Created by huang on 18-10-12.
 */
@Configuration
@EnableWebSecurity
@Import({RestClientForNativeConfig.class
})
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PERMIT_REQUESTS = new String[] {
            "/error",
            "/favicon.ico",
            "/systemInfo/**",
            "/logout",
            "/**/*.html",
            "/**/*.js",
            "/**/*.css",
            "/img/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers(PERMIT_REQUESTS).permitAll()
                .anyRequest().authenticated()
                 .and()
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        ;
//        http.authorizeRequests().anyRequest().authenticated()
//                .and().addFilter().addFilterAfter()
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public CollabCallHueNativeAuthenticationSuccessHandler successHandler() {
        return new CollabCallHueNativeAuthenticationSuccessHandler();
    }

    @Bean
    public CollabCallHueNativeAuthenticationFailureHandler failureHandler() {
        return new CollabCallHueNativeAuthenticationFailureHandler();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CollabCallHueNativeAuthenticationProvider();
    }

    @Bean
    public CollabCallHueNativeAuthenticationFilter authenticationFilter() throws Exception {
        CollabCallHueNativeAuthenticationFilter filter = new CollabCallHueNativeAuthenticationFilter(PERMIT_REQUESTS);
        filter.setAuthenticationFailureHandler(new ChainAuthenticationFailureHandler(failureHandler()));
        filter.setAuthenticationSuccessHandler(new ChainAuthenticationSuccessHandler(successHandler()));
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }






//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("test1")
//                .password("test1")
//                .roles("USER")
//                .and()
//                .withUser("test2")
//                .password("test2")
//                .roles("USER", "ADMIN");
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

}
