package cn.saintshaga.auth.security.matcher;

import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by huang on 18-10-19.
 */
public class NotRequestMatcher implements RequestMatcher {

    private final RequestMatcher requestMatcher;

    public NotRequestMatcher(RequestMatcher requestMatcher) {
        this.requestMatcher = requestMatcher;
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        Assert.notNull(requestMatcher, "requestMatcher must not null.");
        return !requestMatcher.matches(request);
    }

    @Override
    public String toString() {
        return "NotRequestMatcher [requestMatcher=" + requestMatcher + "]";
    }

}
