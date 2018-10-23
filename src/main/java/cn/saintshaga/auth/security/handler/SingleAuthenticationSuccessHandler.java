package cn.saintshaga.auth.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Created by huang on 18-10-23.
 */
public interface SingleAuthenticationSuccessHandler extends AuthenticationSuccessHandler {
    boolean supports(Authentication authentication);
}
