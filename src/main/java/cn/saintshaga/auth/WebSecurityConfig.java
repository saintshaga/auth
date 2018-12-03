package cn.saintshaga.auth;

import com.google.common.collect.Sets;
import com.worksap.company.hue.security.config.AuthFilterConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.Set;

/**
 * Created by huang on 18-10-24.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends AuthFilterConfiguration {

    @Override
    protected Set<String> getCsrfAllowedMethods() {
        return Sets.newHashSet("GET", "HEAD", "TRACE", "OPTIONS", "DELETE", "POST");
    }
}
