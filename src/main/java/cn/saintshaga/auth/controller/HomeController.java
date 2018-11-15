package cn.saintshaga.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huang on 18-10-16.
 */
@RestController
public class HomeController {

    @RequestMapping("/home")
    public String home(Authentication authentication) {
        return (String) authentication.getCredentials();
//        return "hello " + authentication.getName() + ":"
//                + LocaleContextHolder.getLocale().toString()
//                + ":" + LocaleContextHolder.getTimeZone()
//                + ":" + authentication.getCredentials();
    }


}
