package cn.saintshaga.auth;

import com.worksap.company.hue.security.config.AuthFilterConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Created by huang on 18-10-24.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends AuthFilterConfiguration {
}
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    private static final String[] PERMIT_REQUESTS = new String[] {
//            "/error",
//            "/favicon.ico",
//            "/systemInfo/**",
//            "/logout",
//            "/**/*.html",
//            "/**/*.js",
//            "/**/*.css",
//            "/img/**"
//    };
//    /**
//     * Allow sub class to configure which urls are allowed to no authenticated.
//     * For those urls, they can be accessed directly without any authentication.
//     * Those urls will also be ignored in the customized filter {@link CollabCallHueNativeAuthenticationFilter}.
//     */
//    protected String[] getPermitRequests() {
//        return PERMIT_REQUESTS;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests().antMatchers(getPermitRequests()).permitAll()
//                .anyRequest().authenticated()
//                .and().formLogin()
//        ;
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
//        return manager;
//    }
//
//}
