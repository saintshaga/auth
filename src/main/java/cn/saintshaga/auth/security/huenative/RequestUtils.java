package cn.saintshaga.auth.security.huenative;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by huang on 18-10-19.
 */
public class RequestUtils {

    public static boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(
                request.getHeader("x-requested-with"));
    }
}
