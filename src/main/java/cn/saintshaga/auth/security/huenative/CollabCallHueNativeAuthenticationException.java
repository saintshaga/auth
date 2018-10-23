package cn.saintshaga.auth.security.huenative;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;

/**
 * Thrown if an authentication request to hue native failed due to a system problem.
 * <p>
 * This might be thrown if the authentication is unauthorized.
 * Whether to throw this exception when hue native auth is not accessible depends on
 * detailed requirements.
 *
 */

public class CollabCallHueNativeAuthenticationException extends InternalAuthenticationServiceException {
    // ~ Constructors
    // ===================================================================================================

    /**
     * Constructs an <code>CollabCallHueNativeAuthenticationException</code> with the specified
     * message.
     *
     * @param msg the detail message
     */
    public CollabCallHueNativeAuthenticationException(String msg) {
        super(msg);
    }

    /**
     * Constructs an <code>CollabCallHueNativeAuthenticationException</code> with the specified
     * message and root cause.
     *
     * @param msg the detail message
     * @param t root cause
     */

    public CollabCallHueNativeAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

}
