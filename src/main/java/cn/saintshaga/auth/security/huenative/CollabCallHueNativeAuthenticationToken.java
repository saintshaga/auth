package cn.saintshaga.auth.security.huenative;

import com.worksap.company.hue.user.context.JWTClaim;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * Created by huang on 18-10-15.
 */
@Getter
public class CollabCallHueNativeAuthenticationToken extends AbstractAuthenticationToken {

    private final CollabUserDetails principal;

    private String credentials;

    @Setter
    private boolean needHueNativeAuthentication = false;

    @Setter
    private String hueNativeToken;

    public CollabCallHueNativeAuthenticationToken(CollabUserDetails principal, String hueNativeToken) {
        this(principal, hueNativeToken, false);
    }

    public CollabCallHueNativeAuthenticationToken(CollabUserDetails principal, String hueNativeToken, boolean needHueNativeAuthentication) {
        super(null);
        this.principal = principal;
        this.hueNativeToken = hueNativeToken;
        this.needHueNativeAuthentication = needHueNativeAuthentication;
    }

}
