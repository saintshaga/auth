package cn.saintshaga.auth.security.huenative;

import com.google.common.base.Strings;
import com.worksap.company.hue.endpoint.CollaboWebApiEndpoint;
import com.worksap.company.hue.restclient.client.RestClient;
import com.worksap.company.hue.user.context.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.client.HttpClientErrorException;

public class CollabCallHueNativeAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private RestClient restClient;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CollabCallHueNativeAuthenticationToken authenticationToken = (CollabCallHueNativeAuthenticationToken) authentication;
        CollabUserDetails userDetails = authenticationToken.getPrincipal();

        if(Strings.isNullOrEmpty(authenticationToken.getHueNativeToken())) {
            throw new CollabCallHueNativeAuthenticationException("No hue native token.");
        }
        UserContext userContext = userDetails.getUser();
        if(authenticationToken.isNeedHueNativeAuthentication() || userContext == null || !userDetails.isCredentialsNonExpired()) {
            try {
                userContext = restClient.get("collabo", CollaboWebApiEndpoint.USER_CONTEXT,
                        UserContext.class, authenticationToken.getHueNativeToken());
            } catch(HttpClientErrorException e1) {
                if(e1.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                    throw new CollabCallHueNativeAuthenticationException("Authenticated failed from hue native auth.");
                } else {
                    //for non-authentication failed case, return null so that other provider can continue authentication
                    return null;
                }
            } catch(Exception e2) {
                return null;
            }
        }

        CollabUserDetails principal = new CollabUserDetails(userContext);

        return createSuccessAuthentication(authenticationToken, principal);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CollabCallHueNativeAuthenticationToken.class.equals(authentication);
    }

    private Authentication createSuccessAuthentication(CollabCallHueNativeAuthenticationToken authentication,
                                                       CollabUserDetails principal) {
        CollabCallHueNativeAuthenticationToken result = new CollabCallHueNativeAuthenticationToken(principal,
                authentication.getHueNativeToken(), false);
        result.setDetails(authentication.getDetails());
        result.setAuthenticated(true);
        return result;
    }
}
