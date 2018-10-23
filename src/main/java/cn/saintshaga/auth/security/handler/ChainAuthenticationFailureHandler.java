package cn.saintshaga.auth.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by huang on 18-10-23.
 */
public class ChainAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private final List<SingleAuthenticationFailureHandler> failureHandlers;

    public ChainAuthenticationFailureHandler(SingleAuthenticationFailureHandler... handlers) {
        this.failureHandlers = Arrays.asList(handlers);
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(!CollectionUtils.isEmpty(this.failureHandlers)) {
            for(SingleAuthenticationFailureHandler handler : this.failureHandlers) {
                if(handler.supports(exception)) {
                    handler.onAuthenticationFailure(request, response, exception);
                    return;
                }
            }
        }
        super.onAuthenticationFailure(request, response, exception);
    }
}
