package cn.saintshaga.auth.security.huenative;

import com.worksap.company.hue.user.context.JWTClaim;
import com.worksap.company.hue.user.context.UserContext;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by huang on 18-10-17.
 */
public class CollabUserDetails implements UserDetails, CredentialsContainer {

    @Getter
    private UserContext user;

    @Setter
    private Long expiredTime;

    public CollabUserDetails(JWTClaim jwtClaim) {
        if(jwtClaim != null) {
            this.user = jwtClaim.getContext();
            this.expiredTime = jwtClaim.getExt();
        }
    }

    public CollabUserDetails(UserContext userContext) {
        this.user = userContext;
        this.expiredTime = System.currentTimeMillis();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.NO_AUTHORITIES;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return user.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.expiredTime != null && this.expiredTime >= System.currentTimeMillis();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void eraseCredentials() {
    }
}
