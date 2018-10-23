package cn.saintshaga.auth.security.huenative;

import cn.saintshaga.auth.security.matcher.NotRequestMatcher;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.worksap.company.hue.constant.CookieKey;
import com.worksap.company.hue.filter.service.impl.TokenServiceImpl;
import com.worksap.company.hue.jwt.JWTUtil;
import com.worksap.company.hue.tenant.Tenant;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by huang on 18-10-15.
 */
public class CollabCallHueNativeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public CollabCallHueNativeAuthenticationFilter() {
        super("/**");
    }

    public CollabCallHueNativeAuthenticationFilter(String... skippedFilterAntPatterns) {
        this();
        List<RequestMatcher> matchers = Lists.newArrayList();
        for(String antPattern : skippedFilterAntPatterns) {
            matchers.add(new AntPathRequestMatcher(antPattern));
        }
        this.setRequiresAuthenticationRequestMatcher(new NotRequestMatcher(new OrRequestMatcher(matchers)));
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        Tenant tenant = this.getTenant(request);
//        if(tenant == null) {
//            throw new AuthenticationServiceException("Can not get tenant from host:" + request.getHeader("host"));
//        }
        CollabUserDetails principal = new CollabUserDetails(JWTUtil.parseJWT(this.getJWTTOken(request)));

        CollabCallHueNativeAuthenticationToken authenticationToken = new CollabCallHueNativeAuthenticationToken(
                principal,
                this.getHueNativeToken(request),
                !RequestUtils.isAjaxRequest(request));

        authenticationToken.setDetails(tenant);
        return getAuthenticationManager().authenticate(authenticationToken);
    }

    private Tenant getTenant(HttpServletRequest request) {
        String host = request.getHeader("host");
        if(!Strings.isNullOrEmpty(host)) {
            //TODO: [Must]Analyze host to get necessary tenant information
        }
        return null;
    }

    private String getJWTTOken(HttpServletRequest request) {
        //TODO: [Must]Improve the code style
        return new TokenServiceImpl().getJWTToken(request);
    }

    private String getHueNativeToken(HttpServletRequest request) {
        Cookie cookie = this.getCookieByName(request, CookieKey.TOKEN.toString());
        return cookie == null ? null : cookie.getValue();

    }

    private Cookie getCookieByName(HttpServletRequest request, String name) {
        Cookie []cookies = request.getCookies();
        if(cookies == null) {
            return null;
        }

        for(Cookie cookie : cookies) {
            if(name.equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }

}
