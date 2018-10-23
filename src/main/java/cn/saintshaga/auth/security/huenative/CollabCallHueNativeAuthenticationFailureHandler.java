package cn.saintshaga.auth.security.huenative;

import cn.saintshaga.auth.security.handler.SingleAuthenticationFailureHandler;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huang on 18-10-19.
 */
public class CollabCallHueNativeAuthenticationFailureHandler implements SingleAuthenticationFailureHandler {

    private static final String LOGIN_PAGE = "/SSOTemplate.html";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(RequestUtils.isAjaxRequest(request)) {
            response.setHeader("REDIRECT", "REDIRECT");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            String from = request.getRequestURL().toString();
            String query = request.getQueryString();
            if (query != null && !query.isEmpty()) {
                from = from + "?" +query;
            }
            response.sendRedirect(request.getContextPath() + LOGIN_PAGE + "?from=" + from);
        }
    }

    @Override
    public boolean supports(AuthenticationException exception) {
        return exception instanceof CollabCallHueNativeAuthenticationException;
    }
}
