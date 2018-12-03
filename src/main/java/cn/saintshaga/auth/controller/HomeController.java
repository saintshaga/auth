package cn.saintshaga.auth.controller;

import cn.saintshaga.auth.event.CustomEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huang on 18-10-16.
 */
@RestController
public class HomeController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @RequestMapping("/home")
    public String home(Authentication authentication) {
        return (String) authentication.getCredentials();
//        return "hello " + authentication.getName() + ":"
//                + LocaleContextHolder.getLocale().toString()
//                + ":" + LocaleContextHolder.getTimeZone()
//                + ":" + authentication.getCredentials();
    }

    @RequestMapping("sendEvent")
    public String sendEvent(@RequestParam(required = false) String message) {
        publisher.publishEvent(new CustomEvent(this, message));
        return "asdf";
    }

    @RequestMapping(value = "deleteTest", method = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST})
    public String deleteTest() {
        return "deleteSuccessful";
    }



}
