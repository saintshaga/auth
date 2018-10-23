package cn.saintshaga.auth.security.huenative;

import cn.saintshaga.auth.security.handler.SingleAuthenticationSuccessHandler;
import com.worksap.company.hue.constant.CookieKey;
import com.worksap.company.hue.jwt.JWTUtil;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huang on 18-10-19.
 */
public class CollabCallHueNativeAuthenticationSuccessHandler implements SingleAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //TODO: [Problem] On production environment, it doesn't work
        Cookie cookie = new Cookie(CookieKey.COLLABO_TOKEN.toString(), JWTUtil.generateJWT(
                ((CollabCallHueNativeAuthenticationToken)authentication).getPrincipal().getUser()));
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public boolean supports(Authentication authentication) {
        return authentication instanceof CollabCallHueNativeAuthenticationToken;
    }
}
