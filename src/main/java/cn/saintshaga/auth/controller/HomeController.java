package cn.saintshaga.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by huang on 18-10-16.
 */
@RestController
public class HomeController {
    @RequestMapping("/home")
    public String home(Principal principal) {
        return "hello " + principal.getName();
    }



}
