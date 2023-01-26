package com.jhs.mokoji.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/sign")
    public String signUpPage(){
        return "signUp";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/userInfo")
    public String userInfo(){
        return "user/userInfo";
    }
}
