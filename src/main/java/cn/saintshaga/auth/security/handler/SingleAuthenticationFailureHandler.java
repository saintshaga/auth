package cn.saintshaga.auth.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * Created by huang on 18-10-23.
 */
public interface SingleAuthenticationFailureHandler extends AuthenticationFailureHandler {
    boolean supports(AuthenticationException authenticationException);
}
