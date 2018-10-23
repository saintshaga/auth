package cn.saintshaga.auth.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
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
public class ChainAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final List<SingleAuthenticationSuccessHandler> successHandlers;

    public ChainAuthenticationSuccessHandler(SingleAuthenticationSuccessHandler... handlers) {
        successHandlers = Arrays.asList(handlers);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if(!CollectionUtils.isEmpty(successHandlers)) {
            for(SingleAuthenticationSuccessHandler handler : successHandlers) {
                if(handler.supports(authentication)) {
                    handler.onAuthenticationSuccess(request, response, authentication);
                    return;
                }
            }
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
